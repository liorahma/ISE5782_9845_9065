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

        //חותך, הקרן מתחילה מבחוץ, לא מאונך
        //TC01: Ray starts outside the tube and crosses it twice
        List<Point> result= tube.findIntersections(new Ray(new Point(0,-2,0), new Vector(2,4,2)));
        assertEquals(2, result.size(),"Wrong number of points in TC01");
        Point p1=new Point(0.6, -0.8, 0.6);
        Point p2=new Point(1, 0, 1);
        assertTrue(List.of(p1, p2).equals(result) || List.of(p2, p1).equals(result),
                "Wrong intersection points in TC01");
        //אותו דבר רק לכיוון השני- לא חותכת
        //TC02: Ray starts outside the tube to the other direction- 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,-2,0), new Vector(-2,-4,-2))),"There should be no intersection in TC02");
        //קרן יוצאת מבפנים
        //TC03: Ray starts inside tube- 1 intersection
        result= tube.findIntersections(new Ray(new Point(0,-0.5,0), new Vector(0,-1.5,2)));
        assertEquals(1,result.size(),"Wrong number of points in TC03");
        assertEquals(new Point(0,-1,2/3f),result.get(1),"Wrong intersection point in TC03");

        // =============== Boundary Values Tests ==================

        //TC04: Ray starts on Tube and goes out- 0 points
        assertNull(tube.findIntersections(new Ray(new Point(0,-1,0), new Vector(0,-1,2))),"There should be no intersection in TC04");
        //TC05: Ray starts on Tube and goes in- 1 point
        result= tube.findIntersections(new Ray(new Point(0,-1,0), new Vector(0,2,-4)));
        assertEquals(1,result.size(),"Wrong number of points in TC05");
        assertEquals(new Point(0,1,-4),result.get(1),"Wrong intersection point in TC05");
        //Parallel vectors:

        //TC06: Ray is inside tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,0.5,0), new Vector(0,0,1))),"There should be no intersection in TC06");

        //TC07: Ray is on tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,1,0), new Vector(0,0,1))),"There should be no intersection in TC07");

        //TC08: Ray is out of tube and parallel to it - 0 intersections
        assertNull(tube.findIntersections(new Ray(new Point(0,2,0), new Vector(0,0,1))),"There should be no intersection in TC08");



    }
}