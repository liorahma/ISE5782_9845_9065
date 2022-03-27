package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PointTest {
    Point _p1 = new Point(1, 2, 3);
    Point _p2 = new Point(5, 7, 9);
    Vector _v = new Vector(4, 5, 6);

    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============

        //Tests that the add works properly
        assertEquals(_p2, _p1.add(_v), "Add() wrong result ");
    }

    /**
     * Test method for {@link primitives.Point#subtract(Point)}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //Tests that subtract works properly
        assertEquals(_v, _p2.subtract(_p1), "subtract() wrong result ");
        // =============== Boundary Values Tests ==================
        //Tests zero vector from subtract for equal points
        assertThrows(IllegalArgumentException.class, () -> _p1.subtract(_p1),
                "subtract() for equal points results in zero vector but does not throw an exception");
    }

    /**
     * Test method for {@link Point#distanceSquared(Point)}
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //Tests that subtract works properly
        double dSquared = (_p2._xyz._d1 - _p1._xyz._d1) * (_p2._xyz._d1 - _p1._xyz._d1) +
                (_p2._xyz._d2 - _p1._xyz._d2) * (_p2._xyz._d2 - _p1._xyz._d2) +
                (_p2._xyz._d3 - _p1._xyz._d3) * (_p2._xyz._d3 - _p1._xyz._d3);
        //double dSquared = 77;
        assertEquals(dSquared, _p2.distanceSquared(_p1), 0.00001, "distanceSquared() wrong result ");
    }

    /**
     * Test method for {@link Point#distance(Point)}
     */
    @Test
    void testDistance() {
        double d = Math.sqrt((_p2._xyz._d1 - _p1._xyz._d1) * (_p2._xyz._d1 - _p1._xyz._d1) +
                (_p2._xyz._d2 - _p1._xyz._d2) * (_p2._xyz._d2 - _p1._xyz._d2) +
                (_p2._xyz._d3 - _p1._xyz._d3) * (_p2._xyz._d3 - _p1._xyz._d3));
        //double d = Math.sqrt(77);
        assertEquals(d, _p2.distance(_p1), 0.00001, "distance() wrong result ");
    }
}