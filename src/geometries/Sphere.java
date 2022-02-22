package geometries;

import primitives.*;

public class Sphere implements Geometry{
    private Point center;
    private double radius;

    public Sphere(Point center, double radius) {
        this.center = center;
        this.radius = radius;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                "center=" + center +
                ", radius=" + radius +
                '}';
    }

    public Point getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public Vector getNormal(Point point){
        return null;
    }
}
