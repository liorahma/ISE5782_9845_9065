package primitives;

import static primitives.Util.isZero;

/**
 * Vector class to represent a direction vector
 */
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


    /**
     * scales vector by 1/length (normalization)
     * @return normalized vector
     */
    public Vector normalize() {
        double length = this.length();
        return new Vector(_xyz.scale(1 / length));
    }

    /**
     *
     * @return vector length
     */
    public double length() {

        return Math.sqrt(this.lengthSquared());
    }

    /**
     *
     * @return vector length squared
     */
    public double lengthSquared() {
        return _xyz._d1 * _xyz._d1 + _xyz._d2 * _xyz._d2 + _xyz._d3 * _xyz._d3;
    }

    /**
     * calculates cross product on 2 vectors
     * @param vector second vector
     * @return a normalized vector orthogonal to both vectors
     */
    public Vector crossProduct(Vector vector) {
        double x = this._xyz._d2 * vector._xyz._d3 - this._xyz._d3 * vector._xyz._d2;
        double y = this._xyz._d3 * vector._xyz._d1 - this._xyz._d1 * vector._xyz._d3;
        double z = this._xyz._d1 * vector._xyz._d2 - this._xyz._d2 * vector._xyz._d1;
        if (isZero(x) && isZero(y) && isZero(z))
            throw new IllegalArgumentException("CrossProduct results with zero vector");
        return new Vector(x, y, z);
    }

    /**
     * Calculates dot product on 2 vectors
     * @param vector second vector for dot product
     * @return double - the result of dot product
     */
    public double dotProduct(Vector vector) {
        return this._xyz._d1 * vector._xyz._d1
                + this._xyz._d2 * vector._xyz._d2
                + this._xyz._d3 * vector._xyz._d3;
    }


    /**
     * Scales vector by factor
     * @param factor factor to scale by
     * @return scaled vector
     */
    public Vector scale(double factor) {
        if (isZero(factor))
            throw new IllegalArgumentException("Cannot scale by zero");
        return new Vector(_xyz.scale(factor));
    }

    /**
     * rotates vector on a given axis by a given angle
     * @param rotationAxis the rotation axis
     * @param angle the rotation angle (degrees)
     * @return the rotated vector
     */
    public Vector rotate(Vector rotationAxis, double angle) {
        // vrot = v*cos(theta) + cross(k,v)*sin(theta) + k*(k.'*v)*(1-cos(theta))
        // v - this
        // k - rotation axis
        // theta - rotation angle in degrees

        // convert to radians
        angle = Math.toRadians(angle);

        double x = this._xyz._d1;
        double y = this._xyz._d2;
        double z = this._xyz._d3;

        double u = rotationAxis._xyz._d1;
        double v = rotationAxis._xyz._d2;
        double w = rotationAxis._xyz._d3;

        double dotProduct = this.dotProduct(rotationAxis);

        double xRotated = u * dotProduct * (1d - Math.cos(angle))  // k*(k.'*v)*(1-cos(theta))
                + x * Math.cos(angle)                              // v*cos(theta)
                + (-w * y + v * z) * Math.sin(angle);              // cross(k,v)*sin(theta)

        double yRotated = v * dotProduct * (1d - Math.cos(angle))  // k*(k.'*v)*(1-cos(theta))
                + y * Math.cos(angle)                              // v*cos(theta)
                + (w * x - u * z) * Math.sin(angle);               // cross(k,v)*sin(theta)

        double zRotated = w * dotProduct * (1d - Math.cos(angle))  // k*(k.'*v)*(1-cos(theta))
                + z * Math.cos(angle)                              // v*cos(theta)
                + (-v * x + u * y) * Math.sin(angle);              // cross(k,v)*sin(theta)

        if(isZero(xRotated) && isZero(yRotated) && isZero(zRotated))
            throw new IllegalArgumentException("rotation results with zero vector");

        return new Vector(xRotated, yRotated, zRotated);
    }

}
