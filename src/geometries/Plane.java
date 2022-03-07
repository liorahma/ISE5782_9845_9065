package geometries;

import primitives.*;

public class Plane implements Geometry {

    private Point _q0;
    private Vector _normal;

    public Plane(Point q0, Vector normal) {
        this._q0 = q0;
        this._normal = normal;
    }

    /**
     * constructs with 3 given points on the plane
     * @param p1 point on the plane
     * @param p2 point on the plane
     * @param p3 point on the plane
     */
    public Plane(Point p1, Point p2, Point p3) {
        Vector v1 = p2.subtract(p1);
        Vector v2 = p3.subtract(p1);
        try {
            _normal = v1.crossProduct(v2).normalize();
        }
        catch (ArithmeticException ex) {
            throw new IllegalArgumentException("Points must define plane (create two linearly independent vectors)");
        }

    }

    public Point getQ0() {
        return _q0;
    }

    /**
     * getter for normal field
     * @return
     */
    public Vector getNormal() {
        return _normal;
    }

    /**
     * overriding {@link Geometry#getNormal(Point)}
     * @param point point from which a normal vector is requested
     * @return
     */
    @Override
    public Vector getNormal(Point point) {
        return getNormal();
    }

    @Override
    public String toString() {
        return "Plane{" +
                "q0=" + _q0 +
                ", normal=" + _normal +
                '}';
    }
}
