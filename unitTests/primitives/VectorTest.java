package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VectorTest {
    Vector _v1 = new Vector(1, 2, 3);
    Vector _v2 = new Vector(-2, -4, -6);
    Vector _v3 = new Vector(0, 3, -2);

    @Test
    void testConstructorZero() {
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Vector(0, 0, 0);
                },
                "ERROR: zero vector should have thrown an exception");
    }

    @Test
    void testAdd() {
    }

    @Test
    void testSubtract() {
    }

    @Test
    void testNormalize() {
    }

    @Test
    void testLength() {
    }

    @Test
    void testLengthSquared() {
    }

    /**
     * test for cross product {@link Vector#crossProduct(Vector)}
     */
    @Test
    void testCrossProduct() {

        // ============ Equivalence Partitions Tests ==============
        Vector vr = _v1.crossProduct(_v2);

        // TC01: Test that length of cross-product is proper (orthogonal vectors taken
        // for simplicity)
        assertEquals(
                _v1.length() * _v2.length(),
                vr.length(),
                0.00001,
                "crossProduct() wrong result length");

    }


    @Test
    void testDotProduct() {
        //check
        //1
    }

    @Test
    void testScale() {
    }
}