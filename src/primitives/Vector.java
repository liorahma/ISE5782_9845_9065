package primitives;

public class Vector extends Point {
    /**
     * Builds representation of a 3D vector
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

    @Override
    public String toString() {
        return "Vector{" + super.toString()
                + "}";
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    /**
     * Adds a vector to a vector
     * @param vector the vector to add
     * @return the resulting
     */
    @Override
    public Vector add(Vector vector) {
        Double3 newXyz = new Double3(vector.xyz.d1 + this.xyz.d1,
                vector.xyz.d2 + this.xyz.d2,
                vector.xyz.d3 + this.xyz.d3);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("addition resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }

    /**
     * Subtracts a given vector from the current vector
     * @param vector the vector to subtract
     * @return the resulting vector
     */
    public Vector subtract(Vector vector) {
        Double3 newXyz = new Double3(vector.xyz.d1 - this.xyz.d1,
                vector.xyz.d2 - this.xyz.d2,
                vector.xyz.d3 - this.xyz.d3);
        if (Double3.ZERO.equals(newXyz)) {
            throw new IllegalArgumentException("subtraction resulting with ZERO vector - not allowed");
        }
        return new Vector(newXyz.d1, newXyz.d2, newXyz.d3);
    }


}
