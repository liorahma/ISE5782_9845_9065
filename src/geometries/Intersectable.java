
package geometries;


import primitives.*;

import java.util.*;


/**
 * abstract class Intersectable - represents any kind of intersectable object
 */
public abstract class Intersectable {
    /**
     * finds intersections between ray and object
     *
     * @param ray ray that intersects
     * @return list of intersection points
     */
    public abstract List<Point> findIntersections(Ray ray);

    public List<GeoPoint> findGeoIntersections(Ray ray) {
        return findGeoIntersectionsHelper(ray, Double.POSITIVE_INFINITY);
    }

    public List<GeoPoint> findGeoIntersections(Ray ray, double maxDistance) {
        return findGeoIntersectionsHelper(ray, maxDistance);
    }

    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance);

    /**
     * static class for point on _geometry
     */
    public static class GeoPoint {
        public Geometry _geometry;
        public Point _point;

        /**
         * constructor for GeoPoint
         *
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this._geometry = geometry;
            this._point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || !(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(_geometry, geoPoint._geometry) && Objects.equals(_point, geoPoint._point);
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
