package primitives;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {
    /**
     * Test method for {@link primitives.Ray#findClosestPoint(List)}
     */
    @Test 
    void testFindClosestPoint() {
        List<Point> points= new ArrayList<Point>();
        Ray ray= new Ray(new Point(0,0,0.5),new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: point is in middle of list
        points.add(new Point(0,0,2));
        points.add(new Point(0,0,1));
        points.add(new Point(0,0,3));
        assertEquals(new Point(0,0,1),ray.findClosestPoint(points),"TC01 Did not return correct point");
        // ============ Boundary Values Tests ==============
        //TC02: list is empty
        assertNull(ray.findClosestPoint(new ArrayList<Point>() ));
        //TC03: first point is closest
        points= new ArrayList<Point>();
        points.add(new Point(0,0,1));
        points.add(new Point(0,0,2));
        points.add(new Point(0,0,3));
        assertEquals(new Point(0,0,1),ray.findClosestPoint(points),"TC03 Did not return correct point");
        //TC04: last point is closest
        points= new ArrayList<Point>();
        points.add(new Point(0,0,3));
        points.add(new Point(0,0,2));
        points.add(new Point(0,0,1));
        assertEquals(new Point(0,0,1),ray.findClosestPoint(points),"TC04 Did not return correct point");

    }
}