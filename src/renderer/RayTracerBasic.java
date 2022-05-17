package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class RayTracerBasic extends RayTracerBase {


    /**
     * initial value for recursion factor
     */
    private static final double INITIAL_K = 1.0;

    /**
     * maximum recursion depth for calColor
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;

    /**
     * stopping value for recursion
     */
    private static final double MIN_CALC_COLOR_K = 0.001;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Method that traces a ray and returns the color that the pixel should be colored in
     *
     * @param ray ray to be traced and colored
     * @return Color for the pixel of a certain ray
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closest = findClosestIntersection(ray);
        if (closest == null)
            return _scene._background;
        return calcColor(closest, ray);
    }


    /**
     * calculates color for given point
     *
     * @param gp  point to calculate color for
     * @param ray camera ray that intersects with the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray) {
        return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, new Double3(INITIAL_K))
                .add(_scene._ambientLight.getIntensity());
    }

    /**
     * calculates color for given point
     *
     * @param point point to calculate color for
     * @param ray   camera ray that intersects with the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint point, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(point, ray, INITIAL_K);
        return 1 == level ? color : color.add(calcGlobalEffects(point, ray.getDir(), level, k));
    }


//    /**
//     * checks if there is an object blocking the light source from the point
//     *
//     * @param gp intersection point on geometry
//     * @param l  vector from light source to geometry
//     * @param n  normal vector to point
//     * @return whether the point should be shaded or not
//     */
//    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
//        Vector lightDir = l.scale(-1);  // starts from point to the light source
//        Ray lightRay = new Ray(gp._point, lightDir, n);
//        // only intersections that are closer than lightsource
//        List<GeoPoint> intersections = _scene._geometries
//                .findGeoIntersections(lightRay, light.getDistance(gp._point));
//        return intersections == null;
//    }

    /**
     * checks if there is an object blocking the light source from the point
     * calculates transparency of object at the point
     *
     * @param gp intersection point on geometry
     * @param ls light source that lights geometry
     * @param l  vector from light source to geometry
     * @param n  normal to point
     * @return the ktr at a given point
     */
    private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
        Vector lightDir = l.scale(-1);  // starts from point to the light source
        Ray lightRay = new Ray(gp._point, lightDir, n);
        List<GeoPoint> intersections = _scene._geometries
                .findGeoIntersections(lightRay, ls.getDistance(gp._point));
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint intersection : intersections) {
            ktr = ktr.product(intersection._geometry.getMaterial()._kt);
            if (ktr.equals(Double3.ZERO))  // already close enough to 0
                return Double3.ZERO;

        }
        return ktr;
    }

    /**
     * calculates color at a certain point
     * taking into account all the local effects of light sources
     *
     * @param gp  the point to calculate lighting for
     * @param ray camera ray that intersects with the given point
     * @return color for the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray, double k) {
        Color color = gp._geometry.getEmission();
        Vector v = ray.getDir();
        Vector n = gp._geometry.getNormal(gp._point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return color;
        Material material = gp._geometry.getMaterial();
        for (LightSource lightSource : _scene._lights) {
            Vector l = lightSource.getL(gp._point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sign(nv)
                Double3 ktr = transparency(gp, lightSource, l, n);
                if (ktr.scale(k).greaterThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(gp._point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }

        }
        return color;
    }

    /**
     * calculates Color, considering global lighting affects
     * calls the recursive calculation
     *
     * @param ray   ray that is being traced
     * @param level maximum level of recursion
     * @param kx    current kx
     * @param kkx   next kx (k * kx)
     * @return Color of point
     */
    private Color calcGlobalEffect(Ray ray, int level, Double3 kx, Double3 kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? _scene._background : calcColor(gp, ray, level - 1, kkx)).scale(kx);
    }

    /**
     * calculates global lighting effects recursively
     *
     * @param gp    point to calculate color for
     * @param v     direction vector of ray intersection the point
     * @param level depth left for recursive calls
     * @param k     current k
     * @return color calculated for the given point
     */
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, Double3 k) {
        Color color = Color.BLACK;
        Vector n = gp._geometry.getNormal(gp._point);
        Material material = gp._geometry.getMaterial();
        Double3 kkr = k.product(material._kr);
        if (kkr.greaterThan(MIN_CALC_COLOR_K))
            color = calcGlobalEffect(constructReflectedRay(gp._point, v, n), level, material._kr, kkr);
        Double3 kkt = k.product(material._kt);
        if (kkt.greaterThan(MIN_CALC_COLOR_K))
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp._point, v, n), level, material._kt, kkt));
        return color;
    }


    /**
     * constructs a refracted ray for a given point and vector
     *
     * @param point point
     * @param v     vector of ray that intersects
     * @param n     normal to point
     * @return refracted ray
     */
    private Ray constructRefractedRay(Point point, Vector v, Vector n) {
        return new Ray(point, v, n);
    }

    /**
     * constructs a reflected ray for a given point and vector
     *
     * @param point point
     * @param v     vector of ray that intersects
     * @param n     normal to point
     * @return reflected ray
     */
    private Ray constructReflectedRay(Point point, Vector v, Vector n) {
        if (isZero(v.dotProduct(n)))
            return new Ray(point, v);
        Vector r = v.subtract(n.scale(v.dotProduct(n)).scale(2)); // r = v - 2 * (v * l) * n
        return new Ray(point, r, n);
    }


    /**
     * calculates diffusive light
     *
     * @param material material of geometry
     * @param nl       dot product of normal to point and vector from light source to point
     * @return factor of diffusive effect
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material._kd.scale(Math.abs(nl));
    }

    /**
     * calculates specular light
     *
     * @param material material of geometry
     * @param n        normal to point
     * @param l        vector from light source to point
     * @param nl       dot product of normal to point and vector from light source to point
     * @param v        direction of camera ray intersecting
     * @return factor of specular effect
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(nl).scale(2)); // r = l - 2 * (n * l) * n
        return material._ks.scale(Math.pow(Math.max(0d, v.scale(-1).dotProduct(r)), material._nshininess));
    }

    /**
     * find the closest point to ray
     *
     * @param ray ray to find closest from
     * @return closest point
     */
    private GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> points = _scene._geometries.findGeoIntersections(ray);
        if (points == null)
            return null;
        return ray.findClosestGeoPoint(points);
    }

}
