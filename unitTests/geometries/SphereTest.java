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
        Sphere s= new Sphere(new Point(0,0,0),2d);
        Point p = new Point(0,0,2);
        Vector v= p.subtract(s.getCenter());
    }
}