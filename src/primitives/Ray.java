package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;

import geometries.Intersectable.GeoPoint;

/**
 * Ray class to represnt a 3D ray
 */
public class Ray {

    private static final double DELTA = 0.1;

    final private Point _p0;
    final private Vector _dir;

    /**
     * constructs Ray with point and vector
     *
     * @param p0  starting point
     * @param dir direction vector
     */
    public Ray(Point p0, Vector dir) {
        this._p0 = p0;
        this._dir = dir.normalize(); //vector must be normalized
    }

    /**
     * constructor for ray, moving starting point in the normal direction by DELTA
     *
     * @param head      p0
     * @param direction dir vector
     * @param normal    normal to point
     */
    public Ray(Point head, Vector direction, Vector normal) {
        Vector dVector = direction.normalize();
        Vector delta = normal.scale(normal.dotProduct(dVector) > 0 ? DELTA : -DELTA);
        Point point = head.add(delta);
        this._p0 = point;
        this._dir = dVector;
    }

    /**
     * getter for p0
     *
     * @return p0
     */
    public Point getP0() {
        return _p0;
    }

    /**
     * getter for dir
     *
     * @return dir
     */
    public Vector getDir() {
        return _dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return Objects.equals(_p0, other._p0) && Objects.equals(_dir, other._dir);
    }

    /**
     * Finds the closest point to start point of ray out of list of points
     *
     * @param points list of points
     * @return closest point, null if list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        return points == null || points.isEmpty() ? null
                : findClosestGeoPoint(points.stream().map(p -> new GeoPoint(null, p)).toList())._point;
    }


    /**
     * Finds the closest point to start point of ray out of list of points
     *
     * @param points list of points
     * @return closest point, null if list is empty
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> points) {
        if (points == null || points.isEmpty())
            return null;
        double distance = _p0.distanceSquared(points.get(0)._point);
        GeoPoint closest = points.get(0);
        for (GeoPoint point : points) {
            double current = _p0.distanceSquared(point._point);
            if (current < distance) {
                distance = current;
                closest = point;
            }
        }
        return closest;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + _p0 +
                ", dir=" + _dir +
                '}';
    }

    public Point getPoint(double t) {
        if (t==0)
            return _p0;
        return _p0.add(_dir.scale(t));
    }
}
