package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * class Cylinder represents a 3D cylinder
 */
public class Cylinder extends Tube {
    private double _height;


    /**
     * constructs a cylinder with Ray, radius and height
     * @param axisRay Ray for axis
     * @param radius radius for base
     * @param height height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        _height = height;
    }


    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                '}';
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }
}
