package geometries;

import primitives.*;

public class Tube implements Geometry {
    protected Ray _axisRay;
    protected double _radius;

    public Tube(Ray axisRay, double radius) {
        this._axisRay = axisRay;
        this._radius = radius;
    }

    public Ray getAxisRay() {
        return _axisRay;
    }

    public double getRadius() {
        return _radius;
    }

    @Override
    public String toString() {
        return "Tube{" +
                "axisRay=" + _axisRay +
                ", radius=" + _radius +
                '}';
    }


    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
