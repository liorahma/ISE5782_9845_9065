package geometries;

import primitives.*;

import java.util.List;

public class Tube extends Geometry {
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
        Vector p0point = point.subtract(_axisRay.getP0());
        double t = p0point.dotProduct(_axisRay.getDir());
        // If p0 is o (the point where the vector p0p is orthogonal to the axis)
        if (t == 0)
            return p0point.normalize();
        Point o = _axisRay.getP0().add(_axisRay.getDir().scale(t));
        return point.subtract(o).normalize();
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
