package geometries;

import primitives.*;

public class Sphere implements Geometry {
    private Point _center;
    private double _radius;

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
}
