package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * Test method for {@link Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point center = new Point(0, 0, 0);
        Sphere s = new Sphere(center, 2d);
        Point point = new Point(0, 0, 2);
        Vector normalOption1 = new Vector(0, 0, 1);
        Vector normalOption2 = new Vector(0, 0, -1);
        Vector sNormal = s.getNormal(point);
        assertTrue(
                sNormal.equals(normalOption1) || sNormal.equals(normalOption2),
                "getNormal() for sphere doesn't work correctly"
        );
    }

    @Test
    void testFindIntersections() {
        Sphere sphere = new Sphere(new Point(1, 0, 0), 1d);

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                        new Vector(1, 1, 0))),
                "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point p1 = new Point(0.0651530771650466, 0.355051025721682, 0);
        Point p2 = new Point(1.53484692283495, 0.844948974278318, 0);
        List<Point> result = sphere.findIntersections(new Ray(new Point(-1, 0, 0),
                new Vector(3, 1, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        // dif (result.get(0)._xyz._d1 > result.get(1).getX())
        //    result = List.of(result.get(1), result.get(0));
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Ray crosses sphere");

        // TC03: Ray starts inside the sphere (1 point)
        result = sphere.findIntersections(new Ray(new Point(1.5d, 0, 0), new Vector(1, 0, 0)));
        assertEquals(
                1,
                result.size(),
                "Wrong number of points in TC03"
        );
        assertEquals(List.of(new Point(2, 0, 0)),
                result,
                "Wrong intersection point in TC03"
        );
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "There should be no intersection in TC04");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1d, -1, 0), new Vector(0.5, 1, 0)));
        assertEquals(
                1,
                result.size(),
                "Wrong number of points in TC11"
        );
        assertEquals(List.of(new Point(0.5294117647, 0.8823529412, 0)),
                result,
                "Wrong intersection point in TC11"
        );
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, -1, 0),
                        new Vector(-0.5, -1, 0))),
                "There should be no intersection in TC12");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        result = sphere.findIntersections(new Ray(new Point(1d, -2, 0), new Vector(0, 1, 0)));
        assertEquals(
                2,
                result.size(),
                "Wrong number of points in TC13"
        );
        p1 = new Point(1, 1, 0);
        p2 = new Point(1, -1, 0);
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Wrong intersection points in TC13"
        );
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(1d, -1, 0), new Vector(0, 1, 0)));
        assertEquals(
                1,
                result.size(),
                "Wrong number of points in TC14"
        );
        assertEquals(List.of(new Point(1, 1, 0)),
                result,
                "Wrong intersection point in TC14"
        );
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntersections(new Ray(new Point(0.25, 0, 0), new Vector(1, 0, 0)));
        assertEquals(
                1,
                result.size(),
                "Wrong number of points in TC15"
        );
        assertEquals(List.of(new Point(2, 0, 0)),
                result,
                "Wrong intersection point in TC15"
        );
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntersections(new Ray(new Point(1, 0, 0), new Vector(1, 0, 0)));
        assertEquals(
                1,
                result.size(),
                "Wrong number of points in TC16"
        );
        assertEquals(List.of(new Point(2, 0, 0)),
                result,
                "Wrong intersection point in TC16"
        );
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(1, 1, 0), new Vector(0, 1, 0))),
                "There should be no intersection in TC17");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 0), new Vector(1, 0, 0))),
                "There should be no intersection in TC18");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(0, 0, 1), new Vector(1, 0, 0))),
                "There should be no intersection in TC19");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(1, 0, 1), new Vector(1, 0, 0))),
                "There should be no intersection in TC20");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntersections(new Ray(new Point(2, 0, 1), new Vector(1, 0, 0))),
                "There should be no intersection in TC21");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntersections(new Ray(new Point(3, 0, 1), new Vector(0, 0, 1))),
                "There should be no intersection in TC22");

    }
}