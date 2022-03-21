package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {


    /**
     * Text method for {@link Plane#Plane(Point, Point, Point)}
     */
    @Test
    void testConstructor3Points() {

        Point p0 = new Point(0, 0, 0);
        Point p1 = new Point(0, 0, 1);
        Point p1Scaled = new Point(0, 0, 2);
        Point p2 = new Point(0, 1, 0);

        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Plane(p0, new Vector(-1d, 0d, 0d)),
                new Plane(p0, p1, p2),
                "Constructor for plane doesn't work correctly"
        );


        // ============ Boundary Values Tests ==============
        // In a case where all 3 points are on the same vector
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(p0, p1, p1Scaled),
                "Constructor of plane allows 3 points on the same vector"
        );

        // In a case where first and second point are the same
        assertThrows(
                IllegalArgumentException.class,
                () -> new Plane(p0, p0, p1Scaled),
                "Constructor of plane allows 2 similar points"
        );
    }

    /**
     * Test method for {@link Plane#getNormal()}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double d = Math.sqrt(1d / 3);
        assertEquals(new Vector(d, d, d), p.getNormal(), "GetNormal() wrong result");

    }

    /**
     * Test method for {@link Plane#getNormal(Point)}
     */
    @Test
    void testGetNormalWithPoint() {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double d = Math.sqrt(1d / 3);
        assertEquals(new Vector(d, d, d), p.getNormal(), "GetNormal(Point) wrong result ");
    }

    /**
     * Test method for {@link Plane#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Plane p = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        // ============ Equivalence Partitions Tests ==============
        // Ray is neither orthogonal nor parallel to the plane
        // EP01: Ray intersects the plane
        List<Point> intersections = p.findIntersections(new Ray(new Point(2, 1, 0), new Vector(-2, -1, 1)));
        assertEquals(intersections.size(),
                1,
                "Incorrect number of intersection points"
        );
        assertEquals(intersections.get(1),
                new Point(0, 0, 1),
                "Incorrect intersection point"
        );
        // EP02: Ray does not intersect the plane
        assertNull(p.findIntersections(new Ray(new Point(2, 1, 0), new Vector(-2, -1, 1))),
                "No intersection - should return null"
        );

        // ============ Boundary Values Tests ==============
        // Ray is parallel to plane
        // BVA01: Ray is included in the plane
        assertNull(p.findIntersections(
                        new Ray(new Point(0, 0, 1), new Vector(1, 0, -1))),
                "Ray is included - should return null"
        );
        // BVA02: Ray is parallel to plane but not included
        assertNull(p.findIntersections(
                        new Ray(new Point(0, 0, 2), new Vector(1, 0, -1))),
                "Ray is parallel - no intersection, should return null"
        );
        // Ray is orthogonal to plane
        // BVA03: Ray intersects plane
        assertEquals(p.findIntersections(
                        new Ray(new Point(0, 0, 1), new Vector(1, 0, -1))).get(1),
                new Point(1d / 3, 1d / 3, 1d / 3),
                "Orthogonal ray with 1 intersection point does not work"
        );
        // BVA04: starting point of ray is on the plane
        assertNull(p.findIntersections(
                        new Ray(new Point(1d / 3, 1d / 3, 1d / 3), new Vector(1, 0, -1))),
                "Orthogonal ray that starts on does not work"
        );
        // BVA05: Ray does not intersect the plane
        assertNull(p.findIntersections(
                        new Ray(new Point(2, 2, 2), new Vector(1, 0, -1))),
                "Orthogonal ray with no intersection does not work"
        );
        // BVA06: Ray starts on plane (and is not orthogonal)
        assertNull(p.findIntersections(
                        new Ray(new Point(0, 1, 0), new Vector(1, 0, 0))),
                "Not orthogonal ray that starts on plane does not work"
        );
        // BVA07: Ray starts on reference point of the plane (and is not orthogonal)
        assertNull(p.findIntersections(
                        new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))),
                "Not orthogonal ray that starts on reference point does not work"
        );
    }
}