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

        // ============ Equivalence Partitions Tests ==============

        // *** Group: Ray starts on the outside, crosses the cylinder and is not orthogonal
        // TC01: Ray starts outside the tube and crosses only the tube, twice - 2 points
        // TC02: Ray starts outside the tube and crosses both bases - 2 points
        // TC03: Ray starts outside the tube and 1 base and the tube - 2 points

        // *** Group: Ray starts on the outside, does not cross the tube and is not orthogonal
        // TC04: Ray starts outside the tube of the cylinder to the other direction - 0 points
        // TC05: Ray starts inside the tube of the but outside the cylinder to the other direction- 0 points

        // *** Group: Ray starts inside the cylinder
        // TC06: Ray crosses the tube - 1 point
        // TC07: Ray crosses one of the bases - 1 point

        // =============== Boundary Values Tests ==================
        // *** Group: Ray starts on the cylinder (not orthogonal or parallel)
        // TC11: Ray starts on Tube and goes out - 0 points
        // TC12: Ray starts on one of the bases and goes out - 0 points
        // TC13: Ray starts on Tube and goes in - 1 point
        // TC14: Ray starts on one of the bases and goes in, crossing other base - 1 point
        // TC15: Ray starts on one of the bases and goes in, crossing tube - 1 point
        // TC16: Ray is on one of the bases - 0 points

        // *** Group: Ray is parallel to cylinder ray (and orthogonal to bases)
        // TC17: Ray is inside tube and parallel to it - 2 points
        // TC18: Ray is on tube and parallel to it - 0 points
        // TC19: Ray is out of cylinder and parallel to it - 0 points

        // *** Group: Ray is orthogonal to cylinder ray
        // TC20: Ray starts out of the tube and crosses the tube - 2 points
        // TC21: Ray starts out of the tube and does not cross the tube - 0 points
        // TC22: Ray starts on the tube and goes in - 1 point
        // TC23: Ray starts on the tube and goes out - 0 points

        // *** Group: Ray starts on cylinder's Ray
        // TC24: Ray starts on Ray and is not orthogonal - 1 intersection
        // TC25: Ray starts on Ray and is orthogonal - 1 intersection
        // TC26: Ray is on ray and starts outside the cylinder, not crossing - 0 points
        // TC27: Ray is on ray and starts outside the cylinder, crossing bases - 2 points
        // TC28: Ray is on ray and starts inside the cylinder, crossing 1 base - 1 point
        // TC29: Ray is on ray and starts on base, going inside - 1 point
        // TC30: Ray is on ray and starts on base, going outside - 0 points


    }
}