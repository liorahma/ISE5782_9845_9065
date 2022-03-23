package primitives;

import java.util.Objects;

public class Point {
    /**
     * 3D representation of the point
     */
    Double3 _xyz;

    /**
     * Builds representation of a 3D point
     *
     * @param x x-axis value
     * @param y y-axis value
     * @param z z-axis value
     */
    public Point(double x, double y, double z) {
        // this(new Double3(x, y, z);
        _xyz = new Double3(x, y, z);
    }

    /**
     * @param xyz tuple for x y z values
     */
    public Point(Double3 xyz) {
        _xyz = new Double3(xyz._d1, xyz._d2, xyz._d3);
    }

    public double getX() {
        return _xyz._d1;
    }

    public double getY() {
        return _xyz._d2;
    }

    public double getZ() {
        return _xyz._d3;
    }

    @Override
    public String toString() {
        return "Point: " + _xyz;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point point = (Point) obj;
        return Objects.equals(_xyz, point._xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_xyz);
    }

    /**
     * Adds a vector to a point
     *
     * @param vector the vector to add
     * @return the end point of the vector starting from the point
     */
    public Point add(Vector vector) {
        Double3 newXyz = vector._xyz.add(this._xyz);
        return new Point(newXyz);
    }

    /**
     * Subtracts a given point from the current point
     *
     * @param point the point to subtract
     * @return the resulting vector
     */
    public Vector subtract(Point point) {
        Double3 newXyz = this._xyz.subtract(point._xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz._d1, newXyz._d2, newXyz._d3);
    }

    /**
     * Calculates the squared value of the distance between 2 points
     *
     * @param point the point to calculate distance from
     * @return the distance squared
     */
    public double distanceSquared(Point point) {
        double deltaX = this._xyz._d1 - point._xyz._d1;
        double deltaY = this._xyz._d2 - point._xyz._d2;
        double deltaZ = this._xyz._d3 - point._xyz._d3;
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }

    /**
     * Calculates the value of the distance between 2 points
     *
     * @param point the point to calculate distance from
     * @return the distance
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }
}
