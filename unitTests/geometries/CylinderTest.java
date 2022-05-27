package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    /**
     * Test method for {@link Cylinder#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
//        // ============ Equivalence Partitions Tests ==============
//        Cylinder c = new Cylinder(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)),
//                2d, 2d);
//
//        // On the side of cylinder (tube)
//        Point pointOnSide = new Point(1, 0, 1);
//        Vector normalOption1 = new Vector(1, 0, 0);
//        Vector normalOption2 = new Vector(-1, 0, 0);
//        Vector sideNormal = c.getNormal(pointOnSide);
//        assertTrue(
//                sideNormal.equals(normalOption1) || sideNormal.equals(normalOption2),
//                "getNormal() of cylinder doesn't work correctly for point on the side"
//        );
//
//        Vector normalForBase = new Vector(0, 0, 1);
//        // On first base - distant
//        Point pointOnBase1 = new Point(1, 0, 2);
//        assertEquals(
//                normalForBase,
//                c.getNormal(pointOnBase1),
//                "getNormal() of cylinder doesn't work correctly for point on distant base"
//        );
//
//        // On second base - the on with p0 on it
//        Point pointOnBase2 = new Point(1, 0, 0);
//        assertEquals(
//                normalForBase,
//                c.getNormal(pointOnBase2),
//                "getNormal() of cylinder doesn't work correctly for point on base with p0"
//        );
    }

    @Test
    void testFindIntersections() {
//        Cylinder cylinder= new Cylinder(new Ray(new Point(0,0,-3), new Vector(0,0,1)),1,6);
//        List<Point> intersections;
//        // ============ Equivalence Partitions Tests ==============
//
//        // *** Group: Ray starts on the outside, crosses the cylinder and is not orthogonal
//        // TC01: Ray starts outside the tube and crosses only the tube, twice - 2 points
//        List<Point> result = cylinder.findIntersections(new Ray(new Point(0, -2, 0), new Vector(2, 4, 2)));
//        //assertEquals(2, result.size(), "Wrong number of points in TC01");
//        Point p1 = new Point(0.6, -0.8, 0.6);
//        Point p2 = new Point(1, 0, 1);
////        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
////                "Wrong intersection points in TC01");
//        // TC02: Ray starts outside the tube and crosses both bases - 2 points
//        result = cylinder.findIntersections(new Ray(new Point(0.5, 0, -5), new Vector(0, 0.1, 10)));
//        //assertEquals(2, result.size(), "Wrong number of points in TC02");
//        p1 = new Point(0.5,0.02,-3);
//        p2 = new Point(0.5,0.08,3);
//        assertEquals(List.of(p1),result);
//        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
//                "Wrong intersection points in TC02");
//
//        // TC03: Ray starts outside the tube and 1 base and the tube - 2 points
//        result = cylinder.findIntersections(new Ray(new Point(-2, 0, 0), new Vector(4, 0, 5)));
//        assertEquals(2, result.size(), "Wrong number of points in TC03");
//        p1 = new Point(0.4,0,3);
//        p2 = new Point(-1,0,1.25);
//        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
//                "Wrong intersection points in TC03");
//        // *** Group: Ray starts on the outside, does not cross the tube and is not orthogonal
//        // TC04: Ray starts outside the tube of the cylinder to the other direction - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(2,0,0),new Vector(2,0,1))),"TC04 doesn't return null");
//        // TC05: Ray starts inside the tube of the but outside the cylinder to the other direction- 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,0.5,4),new Vector(2,-0.5,1))),"TC05 doesn't return null");
//        // *** Group: Ray starts inside the cylinder
//        // TC06: Ray crosses the tube - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0, 0, 1), new Vector(2, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC06");
//        assertEquals(new Point(1, 0, 1.5), result.get(0), "Wrong intersection point in TC06");
//        // TC07: Ray crosses one of the bases - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0, 0, 1), new Vector(0.5, 1, 3)));
//        assertEquals(1, result.size(), "Wrong number of points in TC07");
//        assertEquals(new Point(1d/3, 2d/3, 3), result.get(0), "Wrong intersection point in TC07");
//        // =============== Boundary Values Tests ==================
//        // *** Group: Ray starts on the cylinder (not orthogonal or parallel)
//
//        // TC11: Ray starts on Tube and goes out - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(1,0,0),new Vector(1,0,1))),"TC11 doesn't return null");
//        // TC12: Ray starts on one of the bases and goes out - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(1,0,3),new Vector(1,0,1))),"TC11 doesn't return null");
//        // TC13: Ray starts on Tube and goes in - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(1, 0, 0), new Vector(-1, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC13");
//        assertEquals(new Point(-1, 0, 2), result.get(0), "Wrong intersection point in TC13");
//        // TC14: Ray starts on one of the bases and goes in, crossing other base - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0.5, 0.2, -3), new Vector(0, 0.1, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC14");
//        assertEquals(new Point(0.5, 0.8, 3), result.get(0), "Wrong intersection point in TC14");
//        // TC15: Ray starts on one of the bases and goes in, crossing tube - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0, 0, -3), new Vector(1, 0, 3)));
//        assertEquals(1, result.size(), "Wrong number of points in TC15");
//        assertEquals(new Point(1, 0, 0), result.get(0), "Wrong intersection point in TC15");
//        // TC16: Ray is on one of the bases - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,1,3),new Vector(1,-1,0))),"TC16 doesn't return null");
//        // *** Group: Ray is parallel to cylinder ray (and orthogonal to bases)
//        // TC17: Ray is inside tube and parallel to it - 2 points
//        result = cylinder.findIntersections(new Ray(new Point(0, 0.5, -4), new Vector(0, 0, 1)));
//        assertEquals(2, result.size(), "Wrong number of points in TC17");
//        p1 = new Point(0,0.5,-3);
//        p2 = new Point(0,0.5,3);
//        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
//                "Wrong intersection points in TC17");
//        // TC18: Ray is on tube and parallel to it - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,1,-4),new Vector(0,0,1))),"TC18 doesn't return null");
//        // TC19: Ray is out of cylinder and parallel to it - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,2,-4),new Vector(0,0,1))),"TC19 doesn't return null");
//
//        // *** Group: Ray is orthogonal to cylinder ray
//        // TC20: Ray starts out of the tube and crosses the tube - 2 points
//        result = cylinder.findIntersections(new Ray(new Point(0, -2, 1), new Vector(0, 1, 0)));
//        assertEquals(2, result.size(), "Wrong number of points in TC20");
//        p1 = new Point(0,-1,1);
//        p2 = new Point(0,1,1);
//        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
//                "Wrong intersection points in TC20");
//        // TC21: Ray starts out of the tube and does not cross the tube - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,2,1),new Vector(0,1,0))),"TC21 doesn't return null");
//        // TC22: Ray starts on the tube and goes in - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0, 1, 1), new Vector(0, 1,0)));
//        assertEquals(1, result.size(), "Wrong number of points in TC22");
//        assertEquals(new Point(0,-1,0), result.get(0), "Wrong intersection point in TC22");
//        // TC23: Ray starts on the tube and goes out - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,1,1),new Vector(0,0,1))),"TC23 doesn't return null");
//
//        // *** Group: Ray starts on cylinder's Ray (same start point)
//        // TC24: Ray starts on Ray and is not orthogonal - 1 intersection
//        result = cylinder.findIntersections(new Ray(new Point(0,0,0), new Vector(0, 1, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC24");
//        assertEquals(new Point(0,1,1), result.get(0), "Wrong intersection point in TC24");
//        // TC25: Ray starts on Ray and is orthogonal - 1 intersection
//        result = cylinder.findIntersections(new Ray(new Point(0,0,0), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC25");
//        assertEquals(new Point(0,0,1), result.get(0), "Wrong intersection point in TC25");
//        // TC26: Ray is on ray and starts on base, going inside with the ray- 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0,0,0), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC26");
//        assertEquals(new Point(0,0,3), result.get(0), "Wrong intersection point in TC26");
//        // TC27: Ray is on ray and starts on base, going outside - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,0,0),new Vector(0,-1,1))),"TC27 doesn't return null");
//
//        // *** Group: Ray is on cylinder's Ray (share part of the line)
//        // TC28: Ray is on ray and starts outside the cylinder, not crossing - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,0,4),new Vector(0,0,1))),"TC28 doesn't return null");
//        // TC29: Ray is on ray and starts outside the cylinder, crossing bases - 2 points
//        result = cylinder.findIntersections(new Ray(new Point(0, 0, -5), new Vector(0, 0, 1)));
//        assertEquals(2, result.size(), "Wrong number of points in TC29");
//        p1 = new Point(0,0,-3);
//        p2 = new Point(0,0,3);
//        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
//                "Wrong intersection points in TC29");
//        // TC30: Ray is on ray and starts inside the cylinder, crossing 1 base - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0,0,1), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC30");
//        assertEquals(new Point(0,1,1), result.get(0), "Wrong intersection point in TC30");
//        // TC31: Ray is on ray and starts on base, going inside - 1 point
//        result = cylinder.findIntersections(new Ray(new Point(0,0,-3), new Vector(0, 0, 1)));
//        assertEquals(1, result.size(), "Wrong number of points in TC31");
//        assertEquals(new Point(0,0,31), result.get(0), "Wrong intersection point in TC31");
//        // TC32: Ray is on ray and starts on base, going outside - 0 points
//        assertNull(cylinder.findIntersections(new Ray(new Point(0,0,3),new Vector(0,0,1))),"TC32 doesn't return null");

    }
}