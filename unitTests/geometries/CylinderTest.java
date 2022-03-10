package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class CylinderTest {

    @Test
    void testGetNormal() {
        Cylinder c= new Cylinder(new Ray(new Point(0,0,0),new Vector(0,0,1)),2d,2d);
        //assertEquals();
    }
}