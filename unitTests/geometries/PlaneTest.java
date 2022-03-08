package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void testGetNormal() {
        Plane p = new Plane(new Point(0, 0, 1),
                new Point(1, 0, 0),
                new Point(0, 1, 0));
        double d= Math.sqrt(1d/3);
        assertEquals(new Vector(d,d,d), p.getNormal(),"GetNormal() wrong result ");

    }
}