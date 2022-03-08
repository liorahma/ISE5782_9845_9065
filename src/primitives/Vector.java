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
        if (Double3.ZERO.equals(_xyz)) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    /**
     * Builds representation of a 3D vector
     *
     * @param double3 axis values for vector
     */
    public Vector(Double3 double3) {
        super(double3._d1, double3._d2, double3._d3);
        if (Double3.ZERO.equals(new Double3(double3._d1, double3._d2, double3._d3))) {
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }

    @Override
    public String toString() {
        return "Vector: " + _xyz;
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
        Double3 newXyz = _xyz.add(vector._xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("addition resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz._d1, newXyz._d2, newXyz._d3);
    }

    /**
     * Subtracts a given vector from the current vector
     *
     * @param vector the vector to subtract
     * @return the resulting vector
     */
    public Vector subtract(Vector vector) {
        Double3 newXyz = _xyz.subtract(vector._xyz);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz._d1, newXyz._d2, newXyz._d3);
    }


    public Vector normalize() {
        double length = this.length();
        return new Vector(_xyz.scale(1 / length));
    }

    public double length() {

        return Math.sqrt(this.lengthSquared());
    }

    public double lengthSquared() {
        return _xyz._d1 * _xyz._d1 + _xyz._d2 * _xyz._d2 + _xyz._d3 * _xyz._d3;
    }

    public Vector crossProduct(Vector vector) {
        double x = this._xyz._d2 * vector._xyz._d3 - this._xyz._d3 * vector._xyz._d2;
        double y = this._xyz._d3 * vector._xyz._d1 - this._xyz._d1 * vector._xyz._d3;
        double z = this._xyz._d1 * vector._xyz._d2 - this._xyz._d2 * vector._xyz._d1;
        if (x == 0 && y == 0 && z == 0)
            throw new ArithmeticException("CrossProduct results with zero vector");
        return new Vector(x, y, z);
    }

    public double dotProduct(Vector vector) {
        return this._xyz._d1 * vector._xyz._d1
                + this._xyz._d2 * vector._xyz._d2
                + this._xyz._d3 * vector._xyz._d3;
    }

    public Vector scale(double factor) {
        if (factor == 0)
            throw new IllegalArgumentException("Cannot scale by zero");
        return new Vector(_xyz.scale(factor));
    }
}
