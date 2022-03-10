package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

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
}