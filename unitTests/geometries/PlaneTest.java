package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

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
                ()->new Plane(p0, p1, p1Scaled),
                "Constructor of plane allows 3 points on the same vector"
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
    void testGetNormalWithPoint()
    {
        // ============ Equivalence Partitions Tests ==============
        Plane p = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double d = Math.sqrt(1d / 3);
        assertEquals(new Vector(d, d, d), p.getNormal(), "GetNormal(Point) wrong result ");
    }

}