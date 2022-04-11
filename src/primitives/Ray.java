package primitives;

import geometries.Intersectable;

import java.util.List;
import java.util.Objects;
import geometries.Intersectable.GeoPoint;
public class Ray {
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

    public Point getP0() {
        return _p0;
    }

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
     * @param points list of points
     * @return closest point, null if list is empty
     */
    public Point findClosestPoint(List<Point> points) {
        if (points == null || points.isEmpty())
            return null;
        double distance = _p0.distanceSquared(points.get(0));
        Point closest = points.get(0);
        for (Point point : points) {
            double current = _p0.distanceSquared(point);
            if (current < distance) {
                distance = current;
                closest = point;
            }
        }
        return closest;
    }
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
}
