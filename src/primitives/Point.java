package primitives;

import java.util.Objects;

public class Point {
    protected Double3 xyz;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(xyz, point.xyz);
    }

    @Override
    public int hashCode() {
        return Objects.hash(xyz);
    }

    public Point add(Vector vector) {
        Double3 newXyz = vector.xyz.add(this.xyz);
        return new Point(newXyz.d1, newXyz.d2, newXyz.d3);
    }

    public Vector subtract(Point point) {
        Double3 newXyz = point.xyz.subtract(this.xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }
}
