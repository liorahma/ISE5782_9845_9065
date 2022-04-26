package renderer;

import geometries.Intersectable;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;

import static primitives.Util.alignZero;

/**
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class RayTracerBasic extends RayTracerBase {

    private static final double DELTA = 0.1;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;

    public RayTracerBasic(Scene scene) {
        super(scene);
    }

    /**
     * Method that traces a ray and returns the color that the pixel should be colored in
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
     * @param point point to calculate color for
     * @param ray   camera ray that intersects with the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint point, Ray ray, int level, double k) {
        Color color= calcLocalEffects(point, ray);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    /**
     * calculates color for given point
     *
     * @param gp  point to calculate color for
     * @param ray camera ray that intersects with the point
     * @return the color of the point
     */
    private Color calcColor(GeoPoint gp, Ray ray)    {

    }
    /**
     * checks if there is an object blocking the light source from the point
     *
     * @param gp intersection point on geometry
     * @param l  vector from light source to geometry
     * @param n
     * @return weather the point should be shaded or not
     */
    private boolean unshaded(GeoPoint gp, LightSource light, Vector l, Vector n, double nv) {
        Vector lightDir = l.scale(-1);//starts from point to the light source
        Vector epsVector = n.scale(nv < 0 ? DELTA : -DELTA);
        Point point = gp._point.add(epsVector);
        Ray lightRay = new Ray(point, lightDir);
        List<GeoPoint> intersections = _scene._geometries.findGeoIntersections(lightRay,light.getDistance(gp._point));
        return intersections == null;
    }

    /**
     * calculates color at a certain point
     * taking into account all the local effects of light sources
     *
     * @param gp  the point to calculate lighting for
     * @param ray camera ray that intersects with the given point
     * @return color for the point
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
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
                if (unshaded(gp, lightSource, l, n, nv)) {
                    Color iL = lightSource.getIntensity(gp._point);
                    color = color.add(iL.scale(calcDiffusive(material, nl)),
                            iL.scale(calcSpecular(material, n, l, nl, v)));
                }
            }

        }
        return color;
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
     * @param ray ray to find closest from
     * @return closest point
     */
    private GeoPoint findClosestIntersection(Ray ray){
        List<GeoPoint> points = _scene._geometries.findGeoIntersections(ray);
        if (points==null)
            return null;
        return ray.findClosestGeoPoint(points);
    }

}
