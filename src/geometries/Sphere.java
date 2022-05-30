package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;
/**
 * class Sphere represents a 3D sphere
 */
public class Sphere extends Geometry {

    private final Point _center;
    private final double _radius;

    /**
     * Sphere constructor
     * @param center center of sphere
     * @param radius radius of sphere
     */
    public Sphere(Point center, double radius) {
        _center = center;
        _radius = radius;
        if (_bvhIsOn)
            createBoundingBox();
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

    /**
     * get center point of sphere
     * @return center point
     */
    public Point getCenter() {
        return _center;
    }

    /**
     * get radius of sphere
     * @return radius
     */
    public double getRadius() {
        return _radius;
    }

    /**
     * overriding {@link Geometry#getNormal(Point)}
     * @param point point from which a normal vector is requested
     * @return normal
     */
    @Override
    public Vector getNormal(Point point) {
        return point.subtract(_center).normalize();
    }
    /**
     * create boundary box for object
     */
    @Override
    public void createBoundingBox() {
        double minX = _center.getX() - _radius;
        double minY = _center.getY() - _radius;
        double minZ = _center.getZ() - _radius;
        double maxX = _center.getX() + _radius;
        double maxY = _center.getY() + _radius;
        double maxZ = _center.getZ() + _radius;
        _box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }
    /**
     * returns list of intersection with sphere
     * @param ray ray that intersects
     * @return list of intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
        if (_center.equals(ray.getP0())) {//ray starts from center of sphere
            return List.of(ray.getP0().add(ray.getDir().scale(_radius)));
        }
        Vector u = _center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir().normalize());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= _radius)//no intersections
            return null;
        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = tm + th;
        double t2 = tm - th;
        if (t1 <= 0 && t2 <= 0)//both are on 'opposite' side of ray, so it doesn't count as an intersection
            return null;
        if (t1 > 0) {
            if (t2 > 0)
                return List.of(ray.getP0().add(ray.getDir().scale(t1)), ray.getP0().add(ray.getDir().scale(t2)));
            return List.of(ray.getP0().add(ray.getDir().scale(t1)));
        } else if (t2 > 0)
            return List.of(ray.getP0().add(ray.getDir().scale(t2)));
        return null;
    }
    /**
     * returns a list of GeoIntersections with sphere within a certain distance
     * @param ray         ray that intersects
     * @param maxDistance max distance to check
     * @return list of GeoIntersections
     */
    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (_center.equals(ray.getP0())) { // ray starts from center of sphere
            return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(_radius))));
        }
        Vector u = _center.subtract(ray.getP0());
        double tm = u.dotProduct(ray.getDir().normalize());
        double d = Math.sqrt(u.lengthSquared() - tm * tm);
        if (d >= _radius)//no intersections
            return null;
        double th = Math.sqrt(_radius * _radius - d * d);
        double t1 = alignZero(tm + th);
        double t2 = alignZero(tm - th);
        if (t1 <= 0 && t2 <= 0)//both are on 'opposite' side of ray, so it doesn't count as an intersection
            return null;
        if (t1 > 0 && alignZero(t1 - maxDistance) <= 0) {
            if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
                return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t1))),
                        new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t2))));
            return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t1))));

        } else if (t2 > 0 && alignZero(t2 - maxDistance) <= 0)
            return List.of(new GeoPoint(this, ray.getP0().add(ray.getDir().scale(t2))));
        return null;
    }
}
