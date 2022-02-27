package primitives;

public class Vector extends Point {
    /**
     * Builds representation of a 3D vector
     *
     * @param x x-axis value
     * @param y y-axis value
     * @param z z-axis value
     */
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Double3.ZERO.equals(new Double3(x, y, z))) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    public Vector(Double3 newVector) {
        super(newVector.d1, newVector.d2, newVector.d3);
        if (Double3.ZERO.equals(new Double3(newVector.d1, newVector.d2, newVector.d3))) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    @Override
    public String toString() {
        return "Vector{" + super.toString()
                + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return super.equals(obj);
    }

    /**
     * Adds a vector to a vector
     *
     * @param vector the vector to add
     * @return the resulting
     */
    @Override
    public Vector add(Vector vector) {
        Double3 newXyz = xyz.add(vector.xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("addition resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }

    /**
     * Subtracts a given vector from the current vector
     *
     * @param vector the vector to subtract
     * @return the resulting vector
     */
    public Vector subtract(Vector vector) {
        Double3 newXyz = xyz.subtract(vector.xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }


    public Vector normalize() {
        double length = this.length();
        return new Vector(xyz.scale(1 / length));
    }

    public double length() {

        return Math.sqrt(this.lengthSquared());
    }

    public double lengthSquared() {
        return xyz.d1 * xyz.d1 + xyz.d2 * xyz.d2 + xyz.d3 * xyz.d3;
    }

    public Vector crossProduct(Vector vector) {
        double x = this.xyz.d2 * vector.xyz.d3 - this.xyz.d3 * vector.xyz.d2;
        double y = this.xyz.d3 * vector.xyz.d1 - this.xyz.d1 * vector.xyz.d3;
        double z = this.xyz.d1 * vector.xyz.d2 - this.xyz.d2 * vector.xyz.d1;
        if (x == 0 && y == 0 && z == 0)
            throw new ArithmeticException("CrossProduct results with zero vector");
        return new Vector(x, y, z);
    }

    public double dotProduct(Vector vector) {
        return this.xyz.d1 * vector.xyz.d1
                + this.xyz.d2 * vector.xyz.d2
                + this.xyz.d3 * vector.xyz.d3;
    }

    public Vector scale(double factor) {
        if (factor == 0)
            throw new IllegalArgumentException("Cannot scale by zero");
        return new Vector(xyz.scale(factor));
    }
}
