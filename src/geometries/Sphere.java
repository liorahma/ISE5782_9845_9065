package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.isZero;

public class Sphere implements Geometry {

    private final Point _center;
    private final double _radius;

    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + _center +
                ", radius=" + _radius +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sphere sphere = (Sphere) o;
        return isZero(Double.compare(sphere._radius, _radius)) && Objects.equals(_center, sphere._center);
    }
    @Override
    public int hashCode() {
        return Objects.hash(_center, _radius);
    }

    public Point getCenter() {
        return _center;
    }

    public double getRadius() {
        return _radius;
    }


    @Override
    public Vector getNormal(Point point) {
        return point.subtract(_center).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if(_center.equals(ray.getP0())) {
            List<Point> intersections = new ArrayList<>();
            intersections.add(ray.getP0().add(ray.getDir().scale(_radius)));
            return intersections;
        }
        Vector u= _center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir().normalize());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= _radius)//no intersections
            return null;
        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if (t1 <= 0 && t2 <= 0)
            return null;
        List<Point> intersections = new ArrayList<>();
        if (t1 > 0)
            intersections.add(ray.getP0().add(ray.getDir().scale(t1)));
        if (t2 > 0)
            intersections.add(ray.getP0().add(ray.getDir().scale(t2)));
        return intersections;
    }
}
