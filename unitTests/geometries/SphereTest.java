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
    @Test
    void testFindIntersections() {
        Sphere s = new Sphere(new Point(1, 1, 0), 2);
        // ============ Equivalence Partitions Tests ==============
        // EP01: Ray does not intersect sphere

        // EP02: 1 intersection point

        //EP03: Ray continuation to the opposite side does intersect, but the ray does not

        //EP04: 2 intersection points

        // =============== Boundary Values Tests ==================
        //157 slide
        //Point is on the sphere:

        //TC05: P0 is on the sphere, and the vector is not in the direction of inside the sphere- 0 intersections

        //TC06: P0 is on the sphere, and the vector is in the direction of inside the sphere- 1 intersections

        //Vector or opposite vector goes through o:

        //TC07: P0 is o- 1 intersection

        //TC08: P0 is on the sphere and vector is to the outside of the sphere- 0 intersections

        //TC09: P0 is on the sphere and vector is to the inside of the sphere- 1 intersection

        //TC10: P0 is out of the sphere and in the opposite direction- 0 intersections

        //TC11: P0 is out of the sphere and in the direction of the sphere- 2 intersections

        //TC12: P0 is inside the sphere- 1 intersection
        //Tangents:
        //TC13: P0 is before where the ray is tangent to the sphere- 1 intersection

        //TC14: P0 is on the sphere- 0 intersections

        //TC15: P0 is after where the ray is tangent to the sphere- 0 intersection
        //Orthogonal:
        //TC16: Ray is orthogonal to a ray from the middle of the sphere, but does not touch the sphere- 0 intersections











    }
}