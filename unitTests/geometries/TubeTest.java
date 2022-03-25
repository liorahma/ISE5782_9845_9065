package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    /**
     * Test method {@link Tube#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        Tube tube = new Tube(new Ray(new Point(0, 0, 0), new Vector(0, 0, 1)), 2d);
        // ============ Equivalence Partitions Tests ==============

        Point point = new Point(1, 0, 2);
        Vector normalOption1 = new Vector(1, 0, 0);
        Vector normalOption2 = new Vector(-1, 0, 0);
        Vector tubeNormal = tube.getNormal(point);
        assertTrue(
                tubeNormal.equals(normalOption1) || tubeNormal.equals(normalOption2),
                "getNormal() for sphere doesn't work correctly"
        );

        // =============== Boundary Values Tests ==================
        // When vector resulting from subtraction of point and p0 is orthogonal to axisRay vector
        Point boundaryPoint = new Point(1, 0, 0);
        Vector boundaryTubeNormal = tube.getNormal(boundaryPoint);
        assertTrue(
                boundaryTubeNormal.equals(normalOption1) || boundaryTubeNormal.equals(normalOption2),
                "getNormal() for sphere doesn't work correctly for boundary value"
        );
    }
    @Test
    void testFindIntersections() {

        Tube tube= new Tube(new Ray(new Point(0,1,0),new Vector(0,1,0)),1);
        // ============ Equivalence Partitions Tests ==============

        // *** Group: Ray starts on the outside, crosses the tube and is not orthogonal
        // TC01: Ray starts outside the tube and crosses it twice
        List<Point> result= tube.findIntersections(new Ray(new Point(0,-2,0), new Vector(2,4,2)));
        assertEquals(2, result.size(),"Wrong number of points in TC01");
        Point p1=new Point(0.6, -0.8, 0.6);
        Point p2=new Point(1, 0, 1);
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Wrong intersection points in TC01");
        // *** Group: Ray starts on the outside, does not cross the tube and is not orthogonal
        // TC02: Ray starts outside the tube to the other direction - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,-2,0), new Vector(-2,-4,-2))),"There should be no intersection in TC02");

        // TC03: Ray starts inside tube and is not orthogonal - 1 intersection
        result= tube.findIntersections(new Ray(new Point(0,-0.5,0), new Vector(0,-1.5,2)));
        assertEquals(1,result.size(),"Wrong number of points in TC03");
        assertEquals(new Point(0,-1,2/3f),result.get(1),"Wrong intersection point in TC03");

        // =============== Boundary Values Tests ==================

        // *** Group: Ray starts on the tube (not orthogonal)
        // TC11: Ray starts on Tube and goes out - 0 points
        assertNull(tube.findIntersections(new Ray(new Point(0,-1,0), new Vector(0,-1,2))),"There should be no intersection in TC04");
        // TC12: Ray starts on Tube and goes in - 1 point
        result= tube.findIntersections(new Ray(new Point(0,-1,0), new Vector(0,2,-4)));
        assertEquals(1,result.size(),"Wrong number of points in TC05");
        assertEquals(new Point(0,1,-4),result.get(1),"Wrong intersection point in TC05");

        // *** Group: Ray is parallel
        // TC13: Ray is inside tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,0.5,0), new Vector(0,0,1))),"There should be no intersection in TC06");

        // TC14: Ray is on tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,1,0), new Vector(0,0,1))),"There should be no intersection in TC07");

        // TC15: Ray is out of tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,2,0), new Vector(0,0,1))),"There should be no intersection in TC08");

        // *** Group: Ray is orthogonal to tube vector
        // TC16: Ray starts out of the tube and crosses the tube - 2 intersections
        result=tube.findIntersections(new Ray(new Point(2,0,0),new Vector(-3,0,0)));
        assertEquals(2, result.size(),"Wrong number of points in TC16");
        p1=new Point(1,0,0);
        p2=new Point(-1, 0, 0);
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Wrong intersection points in TC16");
        // TC18: Ray starts out of the tube and does not cross the tube - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(2,0,0), new Vector(1,0,0))),"There should be no intersection in TC18");
        // TC19: Ray starts on the tube and goes in - 1 intersection
        result= tube.findIntersections(new Ray(new Point(1,0,0), new Vector(-1,0,0)));
        assertEquals(1,result.size(),"Wrong number of points in TC19");
        assertEquals(new Point(-1,0,0),result.get(1),"Wrong intersection point in TC19");
        // TC20: Ray starts on the tube and goes out - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(1,0,0), new Vector(1,0,0))),"There should be no intersection in TC20");

        // *** Group: Ray starts on tube's Ray
        // TC21: Ray starts on tube's Ray and is not orthogonal - 1 intersection
        result= tube.findIntersections(new Ray(new Point(0,0,1), new Vector(0,-1,-1)));
        assertEquals(1,result.size(),"Wrong number of points in TC21");
        assertEquals(new Point(0,-1,0),result.get(1),"Wrong intersection point in TC21");
        // TC22: Ray starts on tube's Ray and is orthogonal - 1 intersection
        result= tube.findIntersections(new Ray(new Point(0,0,1), new Vector(0,1,0)));
        assertEquals(1,result.size(),"Wrong number of points in TC22");
        assertEquals(new Point(0,1,1),result.get(1),"Wrong intersection point in TC22");
        // TC23: Ray is tube's ray - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,0,1), new Vector(0,0,1))),"There should be no intersection in TC23");

    }
}