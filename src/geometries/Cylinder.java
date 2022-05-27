package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * class Cylinder represents a 3D cylinder
 * We decided not to implement it fully
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * constructs a cylinder with Ray, radius and height
     *
     * @param axisRay Ray for axis
     * @param radius  radius for base
     * @param height  height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this._height = height;
    }

    /**
     * returns height of cylinder
     * @return height
     */
    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                '}';
    }

    /**
     * returns normal vector from give point
     *
     * @param point from which we start the vector
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        // Vector point - p0
        Vector p0point = point.subtract(_axisRay.getP0());
        // Vector point - (p0 + height * dir)
        Vector p0HeightPoint = point.subtract(_axisRay.getP0().add(_axisRay.getDir().scale(_height)));
        // if point is on one of the bases
        if (isZero(p0point.dotProduct(_axisRay.getDir()))
                || isZero(p0HeightPoint.dotProduct(_axisRay.getDir()))) {
            return _axisRay.getDir();
        }
        return super.getNormal(point);
    }

    /**
     * returns list of intersection points between given ray and our geometry
     * @param ray that we check if it intersects
     * @return list of intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }

    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
