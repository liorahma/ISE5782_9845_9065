package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {
    Vector _v1 = new Vector(1, 2, 3);
    Vector _v2 = new Vector(-2, -4, -6);
    Vector _v3 = new Vector(0, 3, -2);

    /**
     * test constructor for zero vector {@link Vector#Vector(double, double, double)}
     */
    @Test
    void testConstructorZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Vector(0, 0, 0);
                },
                "ERROR: zero vector should have thrown an exception");
    }

    /**
     * test for adding 2 vectors {@link Vector#add(Vector)}
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(1, 5, 1),
                _v1.add(_v3),
                "add() results with wrong vector"
        );

        // ============ Boundary Values Tests ==============
        assertThrows(
                IllegalArgumentException.class,
                ()->new Vector(-1, 1, 0).add(new Vector(1, -1, 0)),
                "add() resulting with zero vector does not throw an exception"
        );

    }

    /**
     * test subtraction of vectors {@link Vector#subtract(Vector)}
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(1, -1, 5),
                _v1.subtract(_v3),
                "subtract() results with wrong vector"
        );

        // ============ Boundary Values Tests ==============
        assertThrows(
                IllegalArgumentException.class,
                ()->new Vector(-1, 1, 0).subtract(new Vector(-1, 1, 0)),
                "subtract() resulting with zero vector does not throw an exception"
        );
    }

    /**
     * Test method for normalizing vector {@link Vector#normalize()}
     */
    @Test
    void testNormalize() {
        // ============ Equivalence Partitions Tests ==============

        // Make sure normalized vector length is 1
        assertEquals(
                1d,
                _v1.normalize().length(),
                0.00001,
                "normalize() results with not-normalized vector"
        );
    }

    /**
     * Test method for length calculation {@link Vector#length()}
     */
    @Test
    void testLength() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                Math.sqrt(14),
                _v1.length(),
                0.00001,
                "length() results with incorrect value"
        );
    }


    /**
     * Test method for lengthSquared calculation {@link Vector#lengthSquared()}
     */
    @Test
    void testLengthSquared() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                14d,
                _v1.lengthSquared(),
                0.00001,
                "lengthSquared() results with incorrect value"
        );
    }

    /**
     * test for cross product {@link Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = _v1.crossProduct(_v3);

        // Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(
                _v1.length() * _v3.length(),
                vr.length(),
                0.00001,
                "crossProduct() wrong result length");

        // Test that the cross-product vector is orthogonal to each operand
        assertTrue(isZero(vr.dotProduct(_v1)), "crossProduct() result is not orthogonal to v1");
        assertTrue(isZero(vr.dotProduct(_v3)), "crossProduct() result is not orthogonal to v3");

        // ============ Boundary Values Tests ==============
        assertThrows(
                ArithmeticException.class,
                () -> _v1.crossProduct(_v2),
                "crossProduct() for parallel vectors does not throw an exception"
        );

    }


    /**
     * test for dot product {@link Vector#dotProduct(Vector)}
     */
    @Test
    void testDotProduct() {
        // ============ Equivalence Partitions Tests ==============

        // simple dot product
        assertEquals(
                -28d,
                _v1.dotProduct(_v2),
                0.00001,
                "dotProduct() results with incorrect value"
        );

        // ============ Boundary Values Tests ==============
        // test dor product for orthogonal vectors
        assertEquals(
                0d,
                _v1.dotProduct(_v3),
                0.00001,
                "dotProduct() for orthogonal vectors does not result with 0"
        );

    }

    /**
     * Test method for scaling vector {@link Vector#scale(double)}
     */
    @Test
    void testScale() {
        // ============ Equivalence Partitions Tests ==============
        assertEquals(
                new Vector(2, 4, 6),
                _v1.scale(2),
                "scale() results with wrong vector"
        );

        // ============ Boundary Values Tests ==============
        assertThrows(
                IllegalArgumentException.class,
                ()->_v1.scale(0),
                "scaling vector by zero does not throw an exception"
        );
    }
}