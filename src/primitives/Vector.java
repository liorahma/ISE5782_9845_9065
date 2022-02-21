package primitives;

public class Vector extends Point{
    public Vector(double x, double y, double z) {
        super(x, y, z);
        if (Double3.ZERO.equals(new Double3(x, y, z))){
            throw new IllegalArgumentException("ZERO vector is not allowed");
        }
    }
}
