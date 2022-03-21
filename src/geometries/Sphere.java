package geometries;

import primitives.*;

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
        return null;
    }
}
