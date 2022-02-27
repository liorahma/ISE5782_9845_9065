package geometries;

import java.util.List;

import primitives.*;

import static primitives.Util.*;

public class Plane implements Geometry {

    private Point q0;
    private Vector normal;

    public Plane(Point q0, Vector normal) {
        this.q0 = q0;
        this.normal = normal;
    }

    /**
     * constructs with 3 given points on the plane
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p1.subtract(p3);
        Vector v2 = p1.subtract(p2);
        try {
            this.normal = v1.crossProduct(v2);
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Points must define plane (create two linearly independent vectors)");
        }
        this.q0 = p1;
    }

    public Point getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point point) {
        return null;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + q0 +
                ", normal=" + normal +
                '}';
    }
}
