package primitives;

import java.util.Objects;

public class Point {
    /**
     * 3D representation of the point
     */
    protected Double3 xyz;

    /**
     * Builds representation of a 3D point
     * @param x x-axis value
     * @param y y-axis value
     * @param z z-axis value
     */
    public Point(double x, double y, double z) {
        xyz = new Double3(x, y, z);
    }


    @Override
    public String toString() {
        return "Point{" +
                "xyz=" + xyz +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point)) return false;
        Point point = (Point) obj;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    /**
     * Adds a vector to a point
     * @param vector the vector to add
     * @return the end point of the vector starting from the point
     */
    public Point add(Vector vector) {
        Double3 newXyz = vector.xyz.add(this.xyz);
        return new Point(newXyz.d1, newXyz.d2, newXyz.d3);
    }

    /**
     * Subtracts a given point from the current point
     * @param point the point to subtract
     * @return the resulting vector
     */
    public Vector subtract(Point point) {
        Double3 newXyz = point.xyz.subtract(this.xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }

    /**
     * Calculates the squared value of the distance between 2 points
     * @param point the point to calculate distance from
     * @return the distance squared
     */
    public double distanceSquared(Point point) {
        double deltaX = this.xyz.d1 - point.xyz.d1;
        double deltaY = this.xyz.d2 - point.xyz.d2;
        double deltaZ = this.xyz.d3 - point.xyz.d3;
        return deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
    }

    /**
     * Calculates the value of the distance between 2 points
     * @param point the point to calculate distance from
     * @return the distance
     */
    public double distance(Point point) {
        return Math.sqrt(this.distanceSquared(point));
    }
}
