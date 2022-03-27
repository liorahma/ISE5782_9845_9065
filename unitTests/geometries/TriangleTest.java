package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class TriangleTest {

    /**
     * Test method for {@link Triangle#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Triangle t = new Triangle(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double d = Math.sqrt(1d / 3);
        assertEquals(
                new Vector(d, d, d),
                t.getNormal(new Point(1, 0, 0)),
                "GetNormal() of triangle wrong result - bad normal");
    }

    /**
     * Test method for {@link Triangle#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Triangle t = new Triangle(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        Point p = new Point(0, -1, 0);
        // ============ Equivalence Partitions Tests ==============
        //TC01: Ray intersects inside the triangle
        assertEquals(new Point(1 / 3d, 1 / 3d, 1 / 3d),
                t.findIntersections(new Ray(p, new Vector(1 / 3f, 1 / 3f + 1, 1 / 3f))).get(0));
        //Ray doesn't intersect- two cases:
        //TC02: not on corners
        assertNull(t.findIntersections(new Ray(p, new Vector(1.5, 0.5, 0.5))));
        //TC03: yes on corners
        assertNull(t.findIntersections(new Ray(p, new Vector(1.083043995, 0.9655408618, -0.0485848569))));
        // =============== Boundary Values Tests ==================
        //TC04:Ray intersects on one of the points
        assertNull(t.findIntersections(new Ray(p, new Vector(0, 1, 1))));
        //TC05: Ray intersects on one of the edges
        assertNull(t.findIntersections(new Ray(p, new Vector(0, 1.5, 0.5))));
        //TC06: Ray intersects with continuation of one of the edges
        assertNull(t.findIntersections(new Ray(p, new Vector(2.3, -0.3, 0))));
    }
}
