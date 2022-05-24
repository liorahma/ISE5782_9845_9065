package geometries;

import java.util.ArrayList;
import java.util.List;

import primitives.*;

import static primitives.Util.*;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 *
 * @author Dan
 */
public class Polygon extends Geometry {
    /**
     * List of polygon's vertices
     */
    protected List<Point> _vertices;
    /**
     * Associated plane in which the polygon lays
     */
    protected Plane _plane;
    private int _size;

    /**
     * Polygon constructor based on vertices list. The list must be ordered by edge
     * path. The polygon must be convex.
     *
     * @param vertices list of vertices according to their order by edge path
     * @throws IllegalArgumentException in any case of illegal combination of
     *                                  vertices:
     *                                  <ul>
     *                                  <li>Less than 3 vertices</li>
     *                                  <li>Consequent vertices are in the same
     *                                  point
     *                                  <li>The vertices are not in the same
     *                                  plane</li>
     *                                  <li>The order of vertices is not according
     *                                  to edge path</li>
     *                                  <li>Three consequent vertices lay in the
     *                                  same line (180&#176; angle between two
     *                                  consequent edges)
     *                                  <li>The polygon is concave (not convex)</li>
     *                                  </ul>
     */
    public Polygon(Point... vertices) {
        if (vertices.length < 3)
            throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
        this._vertices = List.of(vertices);
        // Generate the plane according to the first three vertices and associate the
        // polygon with this plane.
        // The plane holds the invariant normal (orthogonal unit) vector to the polygon
        _plane = new Plane(vertices[0], vertices[1], vertices[2]);
        if (vertices.length == 3)
            return; // no need for more tests for a Triangle

        Vector n = _plane.getNormal();

        // Subtracting any subsequent points will throw an IllegalArgumentException
        // because of Zero Vector if they are in the same point
        Vector edge1 = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
        Vector edge2 = vertices[0].subtract(vertices[vertices.length - 1]);

        // Cross Product of any subsequent edges will throw an IllegalArgumentException
        // because of Zero Vector if they connect three vertices that lay in the same
        // line.
        // Generate the direction of the polygon according to the angle between last and
        // first edge being less than 180 deg. It is hold by the sign of its dot product
        // with
        // the normal. If all the rest consequent edges will generate the same sign -
        // the
        // polygon is convex ("kamur" in Hebrew).
        boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
        for (var i = 1; i < vertices.length; ++i) {
            // Test that the point is in the same plane as calculated originally
            if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
                throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
            // Test the consequent edges have
            edge1 = edge2;
            edge2 = vertices[i].subtract(vertices[i - 1]);
            if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
                throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
        }
        _size = vertices.length;
        if (_bvhIsOn)
            createBoundingBox();
    }

    @Override
    public Vector getNormal(Point point) {
        return _plane.getNormal();
    }

    @Override
    public void createBoundingBox() {
        if (_vertices == null)
            return;
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (Point ver : _vertices) {
            minX = Math.min(minX, ver.getX());
            minY = Math.min(minY, ver.getY());
            minZ = Math.min(minZ, ver.getZ());
            maxX = Math.max(maxX, ver.getX());
            maxY = Math.max(maxY, ver.getY());
            maxZ = Math.max(maxZ, ver.getZ());
        }
        _box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        Vector v1 = _vertices.get(_size - 2).subtract(p0);
        Vector v2 = _vertices.get(_size - 1).subtract(p0);
        Vector cross;
        try {
            // if an exception about zero vector was throw, ray point is on the edge
            cross = v2.crossProduct(v1);
        } catch (IllegalArgumentException e) {
            return null;
        }
        double product = alignZero(dir.dotProduct(cross));
        if (isZero(product)) // intersection is on the edge
            return null;

        // first find direction of the normal to 2 adjacent vertices planes with p0
        boolean sign = product > 0;

        for (Point vertex : _vertices) {
            v1 = v2;
            v2 = vertex.subtract(p0);
            try {
                // if an exception about zero vector was throw, ray point is on the edge
                cross = v2.crossProduct(v1);
            } catch (IllegalArgumentException e) {
                return null;
            }
            product = alignZero(dir.dotProduct(cross));
            if (isZero(product)) // intersection is on the edge
                return null;
            // intersection is outside of polygon
            if (sign != product > 0)
                return null;
        }
        return _plane.findIntersections(ray);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        Point p0 = ray.getP0();
        Vector dir = ray.getDir();
        Vector v1 = _vertices.get(_size - 2).subtract(p0);
        Vector v2 = _vertices.get(_size - 1).subtract(p0);
        Vector cross;
        try {
            // if an exception about zero vector was throw, ray point is on the edge
            cross = v2.crossProduct(v1);
        } catch (IllegalArgumentException e) {
            return null;
        }
        double product = alignZero(dir.dotProduct(cross));
        if (isZero(product)) // intersection is on the edge
            return null;

        // first find direction of the normal to 2 adjacent vertices planes with p0
        boolean sign = product > 0;

        for (Point vertex : _vertices) {
            v1 = v2;
            v2 = vertex.subtract(p0);
            try {
                // if an exception about zero vector was throw, ray point is on the edge
                cross = v2.crossProduct(v1);
            } catch (IllegalArgumentException e) {
                return null;
            }
            product = alignZero(dir.dotProduct(cross));
            if (isZero(product)) // intersection is on the edge
                return null;
            // intersection is outside of polygon
            if (sign != product > 0)
                return null;
        }
        List<GeoPoint> intersections = _plane.findGeoIntersections(ray, maxDistance);
        if (intersections == null)
            return null;
        intersections.get(0)._geometry = this;
        return intersections;
    }

}
