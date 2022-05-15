package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BowlingPin extends Geometry {

    private List<Sphere> _spheres;

    public BowlingPin(Ray ray, Material material, Color all, Color special) {
        _spheres = new ArrayList<>();
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(10)), 10).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(13)), 11).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(17)), 12).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(20)), 13).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(22)), 14).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(25)), 15).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(29)), 15.7).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(33)), 15).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(36)), 14).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(38)), 13).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(41)), 12).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(45)), 11).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(48)), 10).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(50)), 9).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(52)), 8).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(54)), 7).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(56)), 6).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(58)), 5).setMaterial(material).setEmission(special));//special
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(62)), 5).setMaterial(material).setEmission(special));//special
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(64)), 6).setMaterial(material).setEmission(special));//special
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(66)), 7).setMaterial(material).setEmission(all));
        _spheres.add((Sphere) new Sphere(ray.getP0().add(ray.getDir().scale(68)), 8).setMaterial(material).setEmission(all));

    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if (_spheres == null)
            return null;
        List<Point> ans = new ArrayList<>();
        List<Point> tmp;
        //for each shape, adds its intersection points to list of intersection points
        for (Intersectable i : _spheres) {
            tmp = i.findIntersections(ray);
            if (tmp != null)
                ans.addAll(tmp);
        }
        if (ans.isEmpty())
            return null;
        return ans;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (_spheres == null)
            return null;
        List<GeoPoint> ans = new LinkedList<>();
        List<GeoPoint> tmp;
        // for each shape, adds its intersection points to list of intersection points
        for (Intersectable i : _spheres) {
            tmp = i.findGeoIntersections(ray, maxDistance);
            if (tmp != null)
                ans.addAll(tmp);
        }
        if (ans.isEmpty())
            return null;
        return ans;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
//    @Override
//    public List<Point> findIntersections(Ray ray) {
//
//        return null;
//    }
//
//    @Override
//    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
//        return null;
//    }
}
