package renderer;

import static primitives.Util.*;

import primitives.*;

public class Camera {
    private Point _location;
    private Vector _vto;
    private Vector _vup;
    private Vector _vright;
    private double _height;
    private double _width;
    private double _distance;

    public Camera(Point location, Vector vto, Vector vup) {
        if (!isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("Vectors must be orthogonal");

        _location = location;
        _vto = vto.normalize();
        _vup = vup.normalize();
        _vright = vto.crossProduct(vup).normalize();
    }

    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    public Ray constructRay(int nX, int nY, int j, int i) {
        return null;
    }
}
