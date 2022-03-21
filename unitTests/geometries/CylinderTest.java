package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)),
                2d, 2d);

        // On the side of cylinder (tube)
        Point pointOnSide = new Point(1, 0, 1);
        Vector normalOption1 = new Vector(1, 0, 0);
        Vector normalOption2 = new Vector(-1, 0, 0);
        Vector sideNormal = c.getNormal(pointOnSide);
        assertTrue(
                sideNormal.equals(normalOption1) || sideNormal.equals(normalOption2),
                "getNormal() of cylinder doesn't work correctly for point on the side"
        );

        Vector normalForBase = new Vector(0, 0, 1);
        // On first base - distant
        Point pointOnBase1 = new Point(1, 0, 2);
        assertEquals(
                normalForBase,
                c.getNormal(pointOnBase1),
                "getNormal() of cylinder doesn't work correctly for point on distant base"
        );

        // On second base - the on with p0 on it
        Point pointOnBase2 = new Point(1, 0, 0);
        assertEquals(
                normalForBase,
                c.getNormal(pointOnBase2),
                "getNormal() of cylinder doesn't work correctly for point on base with p0"
        );
    }
    @Test
    void testFindIntersections() {
    }
}