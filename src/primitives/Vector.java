package primitives;

public class Vector extends Point {
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Double3.ZERO.equals(new Double3(x, y, z))) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    public Vector add(Vector other) {
        Double3 result = super.add(other).xyz;
        return new Vector(xyz.d1, xyz.d2, xyz.d3);
    }
}
