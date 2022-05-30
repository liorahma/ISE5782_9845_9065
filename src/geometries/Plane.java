package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * class Plane represents a 3D plane
 */
public class Plane extends Geometry {
    /**
     * point on plane
     */
    final private Point _q0;
    /**
     * normal to plane
     */
    final private Vector _normal;

    /**
     * plane constructor
     * @param q0 point on plane
     * @param normal normal to plane
     */
    public Plane(Point q0, Vector normal) {
        this._q0 = q0;
        this._normal = normal;
        if (_bvhIsOn)
            createBoundingBox();
    }

    /**
     * constructs with 3 given points on the plane
     *
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        try {
            _normal = v1.crossProduct(v2).normalize();
        } catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Points must define plane (create two linearly independent vectors)");
        }
        _q0 = p1;

    }

    /**
     * getter for q0
     * @return q0
     */
    public Point getQ0() {
        return _q0;
    }

    /**
     * getter for normal field
     *
     * @return normal
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * overriding {@link Geometry#getNormal(Point)}
     * @param point point from which a normal vector is requested
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + _q0 +
                ", normal=" + _normal +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Plane)) return false;
        Plane plane = (Plane) obj;
        return Objects.equals(_q0, plane._q0) && Objects.equals(_normal, plane._normal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_q0, _normal);
    }
    /**
     * create boundary box for object
     */
    @Override
    public void createBoundingBox() {
        _box = null;
    }

    /**
     * returns list of intersection with plane
     * @param ray ray that intersects
     * @return list of intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (_q0.equals(ray.getP0()))//ray starts on point of plain, no intersections
            return null;
        if (isZero(_normal.dotProduct(ray.getDir())))
            return null;
        double t = _normal.dotProduct(_q0.subtract(ray.getP0())) / _normal.dotProduct(ray.getDir());
        if (t <= 0 || isZero(t))//no intersection
            return null;
        return List.of(ray.getP0().add(ray.getDir().scale(t)));

    }
    /**
     * returns a list of GeoIntersections with plane within a certain distance
     * @param ray         ray that intersects
     * @param maxDistance max distance to check
     * @return list of GeoIntersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (_q0.equals(ray.getP0()))//ray starts on point of plain, no intersections
            return null;
        if (isZero(_normal.dotProduct(ray.getDir())))
            return null;
        double t = _normal.dotProduct(_q0.subtract(ray.getP0())) / _normal.dotProduct(ray.getDir());
        if (t <= 0 || isZero(t) || alignZero(t - maxDistance) > 0)  // no intersection or a distant one
            return null;
        return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t))));
    }
}
