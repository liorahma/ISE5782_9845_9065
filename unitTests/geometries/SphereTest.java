package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class SphereTest {
    /**
     * Test method for {@link Sphere#getNormal(Point)}
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        Point center = new Point(0, 0, 0);
        Sphere s = new Sphere(center, 2d);
        Point point = new Point(0, 0, 2);
        Vector normalOption1 = new Vector(0, 0, 1);
        Vector normalOption2 = new Vector(0, 0, -1);
        Vector sNormal = s.getNormal(point);
        assertTrue(
                sNormal.equals(normalOption1) || sNormal.equals(normalOption2),
                "getNormal() for sphere doesn't work correctly"
        );
    }
}