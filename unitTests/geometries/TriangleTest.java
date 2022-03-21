package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
        // ============ Equivalence Partitions Tests ==============
        //Ray intersects inside the triangle
        Point p=new Ray(new Point(0,-1,0);
        Ray checked= new Ray(new Point(0,-1,0),new Vector(1/3,1/3+1,1/3));
        assertEquals(new Point(1/3,1/3,1/3),t.findIntersections(checked));
        //Ray doesn't intersect- two cases:
        //not on corners
        assertEquals(new Point(0.8571428571, -0.1428571429, 0.2857142857),t.findIntersections(new Ray(p,new Vector(1.5,0.5,0.5))));
        //yes on corners
    }
}