
package geometries;


import primitives.*;
import primitives.Vector;

import java.util.*;


/**
 * abstract class Intersectable - represents any kind of intersectable object
 */

public abstract class Intersectable {

    /**
     * a field to turn on and off the bvh
     */
    protected boolean _bvhIsOn = false;
    /**
     * actual boundary box
     */
    public BoundingBox _box;

    /**
     * setter, builder pattern
     *
     * @param bvhIsOn true of false
     * @return this
     */
    public Intersectable setBvhIsOn(boolean bvhIsOn) {
        if (!_bvhIsOn && bvhIsOn) {
            createBoundingBox();
        }
        _bvhIsOn = bvhIsOn;
        return this;
    }

    /**
     * class representing boundary box
     */
    public class BoundingBox {
        /**
         * extreme node of box, containing minimal values for each axis
         */
        public Point _minimums;
        /**
         * extreme node of box, containing maximal values for each axis
         */
        public Point _maximums;

        /**
         * constructor for bounding box
         *
         * @param minimums minimal bounding values
         * @param maximums maximal bounding values
         */
        public BoundingBox(Point minimums, Point maximums) {
            _minimums = minimums;
            _maximums = maximums;
        }
    }

    /**
     * create boundary box for object
     */
    public abstract void createBoundingBox();

    /**
     * return true if ray intersects object
     *
     * @param ray ray to check
     * @return whether ray intersects box
     * code taken from scratchapixel.com
     * https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-acceleration-structure/bounding-volume-hierarchy-BVH-part1
     */
    public boolean isIntersectingBoundingBox(Ray ray) {
        if (!_bvhIsOn || _box == null)
            return true;
        Vector dir = ray.getDir();
        Point p0 = ray.getP0();
        double tmin = (_box._minimums.getX() - p0.getX()) / dir.getX();
        double tmax = (_box._maximums.getX() - p0.getX()) / dir.getX();

        if (tmin > tmax) {
            double temp = tmin;
            tmin = tmax;
            tmax = temp;
        }


        double tymin = (_box._minimums.getY() - p0.getY()) / dir.getY();
        double tymax = (_box._maximums.getY() - p0.getY()) / dir.getY();

        if (tymin > tymax) {
            double temp = tymin;
            tymin = tymax;
            tymax = temp;
        }

        if ((tmin > tymax) || (tymin > tmax))
            return false;

        if (tymin > tmin)
            tmin = tymin;

        if (tymax < tmax)
            tmax = tymax;

        double tzmin = (_box._minimums.getZ() - p0.getZ()) / dir.getZ();
        double tzmax = (_box._maximums.getZ() - p0.getZ()) / dir.getZ();

        if (tzmin > tzmax) {
            double temp = tzmin;
            tzmin = tzmax;
            tzmax = temp;
        }

        if ((tmin > tzmax) || (tzmin > tmax))
            return false;

        if (tzmin > tmin)
            tmin = tzmin;

        if (tzmax < tmax)
            tmax = tzmax;

        return true;
    }


    /**
     * finds intersections between ray and object
     *
     * @param ray ray that intersects
     * @return list of intersection points
     */
    public abstract List<Point> findIntersections(Ray ray);

    /**
     * finds geo intersections between ray and object
     *
     * @param ray ray that intersects
     * @return list of intersection geopoints
     */
    public List<GeoPoint> findGeoIntersections(Ray ray) {
        if (_bvhIsOn && !isIntersectingBoundingBox(ray))
            return null;
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    /**
     * finds intersections between ray and object
     *
     * @param ray         ray that intersects
     * @param maxDistance max distance to check
     * @return list of intersection geopoints
     */
    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        if (_bvhIsOn && !isIntersectingBoundingBox(ray))
            return null;
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    /**
     * finds intersections between ray and object
     *
     * @param ray         ray that intersects
     * @param maxDistance max distance to check
     * @return list of intersection geopoints
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * static class for point on _geometry
     */
    public static class GeoPoint {
        /**
         * geometry on which the point is
         */
        public Geometry _geometry;
        /**
         * coordinates of point
         */
        public Point _point;

        /**
         * constructor for GeoPoint
         *
         * @param geometry geometry point is on
         * @param point point for geoPoint
         */
        public GeoPoint(Geometry geometry, Point point) {
            this._geometry = geometry;
            this._point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) && _point.equals(geoPoint._point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "_geometry=" + _geometry +
                    ", point=" + _point +
                    '}';
        }
    }

}
