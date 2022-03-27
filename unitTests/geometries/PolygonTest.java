package geometries;

import org.junit.jupiter.api.Test;
import primitives.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing Polygons
 *
 * @author Dan Zilberstein
 */
class PolygonTest {
    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor1() {
        // ============ Equivalence Partitions Tests ==============

        // TC01: Correct concave quadrangular with vertices in correct order
        try {
            new Polygon(
                    new Point(0, 0, 1),
                    new Point(1, 0, 0),
                    new Point(0, 1, 0),
                    new Point(-1, 1, 1));
        } catch (IllegalArgumentException e) {
            fail("Failed constructing a correct polygon");
        }
    }

    /**
     * Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}.
     */
    @Test
    public void testConstructor2() {
        // ============ Equivalence Partitions Tests ==============

        // TC02: Wrong vertices order
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                "Constructed a polygon with wrong order of vertices");

        // TC03: Not in the same plane
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                "Constructed a polygon with vertices that are not in the same plane");

        // TC04: Concave quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                        new Point(0.5, 0.25, 0.5)), //
                "Constructed a concave polygon");

        // =============== Boundary Values Tests ==================

        // TC10: Vertex on a side of a quadrangular
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0.5, 0.5)),
                "Constructed a polygon with vertex on a side");

        // TC11: Last point = first point
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                "Constructed a polygon with vertex on a side");

        // TC12: Co-located points
        assertThrows(IllegalArgumentException.class, //
                () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                "Constructed a polygon with vertice on a side");

    }

    /**
     * Test method for {@link geometries.Polygon#getNormal(primitives.Point)}.
     */
    @Test
    public void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: There is a simple single test here
        Polygon pl = new Polygon(
                new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0),
                new Point(-1, 1, 1));
        double sqrt3 = Math.sqrt(1d / 3);
        assertEquals(
                new Vector(sqrt3, sqrt3, sqrt3),
                pl.getNormal(new Point(0, 0, 1)),
                "Bad normal to triangle");
    }
    @Test
    void testFindIntersections() {
        Polygon polygon = new Polygon(new Point(0, 2, 0),
                new Point(2, 0, 0),
                new Point(1, 0, 1),
                new Point(0, 1, 1));
        Point p = new Point(0, -1, 0);
        // ============ Equivalence Partitions Tests ==============
        // TC01: Ray intersects inside the polygon - 1 points
        assertEquals(new Point(0.5, 1, 0.5),
                polygon.findIntersections(new Ray(p, new Vector(1 / 3f, 1 / 3f + 1, 1 / 3f))).get(0));
        // Ray doesn't intersect - two cases:
        // TC02: not on corners
        assertNull(polygon.findIntersections(new Ray(p, new Vector(0, 1, 3))));
        // TC03: on corners
        assertNull(polygon.findIntersections(new Ray(p, new Vector(1, 1, 1))));
        // =============== Boundary Values Tests ==================
        // TC11:Ray intersects on one of the points
        assertNull(polygon.findIntersections(new Ray(p, new Vector(0, 3, 0))));
        // TC12: Ray intersects on one of the edges
        assertNull(polygon.findIntersections(new Ray(p, new Vector(0.5, 1.5, 1))));
        // TC13: Ray intersects with continuation of one of the edges
        assertNull(polygon.findIntersections(new Ray(p, new Vector(0, 1, 2))));


    }
}