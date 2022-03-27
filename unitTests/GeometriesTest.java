import geometries.Geometries;
import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {


    /**
     * Test method for {@link geometries.Geometries#findIntersections(Ray)}
     */
    @Test
    void testFindIntersections() {
        Geometries g= new Geometries();
        Ray r = new Ray(new Point(1,0,0),new Vector(0,0,1));
        // ============ Equivalence Partitions Tests ==============
        //TC01: some of shapes have intersections


        // =============== Boundary Values Tests ==================
        //TC02: geometries list is empty
        assertNull(new Geometries().findIntersections(r),"TC02- does not return null");
        //TC03: No shape intersects

        //TC04: only one shape intersects

        //TC05: all shapes intersect
    }

}