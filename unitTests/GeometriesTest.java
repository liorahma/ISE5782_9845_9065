import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {


    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Geometries g = new Geometries();
        g.add(new Plane(new Point(0.5, 0, 0), new Point(-0.5, 0, 0), new Point(0, 0.5, 0)));
        g.add(new Triangle(new Point(1, 0, 0), new Point(0, -1, 0), new Point(0, 0, 1)));
        g.add(new Sphere(new Point(1, 0, 0), 1));
        Ray r;
        // ============ Equivalence Partitions Tests ==============
        //TC01: some of shapes have intersections
        r = new Ray(new Point(0.5, 0.5, -0.5), new Vector(0, 0, 1));
        assertEquals(2, g.findIntersections(r).size(), "TC01- wrong number of points");
        // =============== Boundary Values Tests ==================
        //TC02: geometries list is empty
        assertNull(new Geometries().findIntersections(r), "TC02- does not return null");
        //TC03: No shape intersects
        r = new Ray(new Point(1.5, 0.5, 2), new Vector(0, 0, 1));
        assertNull(g.findIntersections(r), "TC03 does not return null");
        //TC04: only one shape intersects
        r = new Ray(new Point(2, 2, -0.5), new Vector(0, 0, 1));
        assertEquals(1, g.findIntersections(r).size(), "TC04- wrong number of points");
        //TC05: all shapes intersect
        r = new Ray(new Point(0.7, -0.1, -0.5), new Vector(0, 0, 1));
        assertEquals(3, g.findIntersections(r).size(), "TC05- wrong number of points");
    }

}