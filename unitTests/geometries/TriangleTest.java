package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
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
    @Test
    void testFindIntersections() {
    }
}