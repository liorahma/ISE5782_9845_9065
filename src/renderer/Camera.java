package renderer;

import static primitives.Util.*;

import primitives.*;

/**
 * class for representing camera view
 *
 * @author Liora Mandelbaum and Sarah Bednarsh
 */
public class Camera {
    /**
     * location of camera
     */
    private Point _location;
    /**
     * z-axis vector
     */
    private Vector _vto;
    /**
     * y-axis vector
     */
    private Vector _vup;
    /**
     * x-axis vector
     */
    private Vector _vright;
    /**
     * height of view plane
     */
    private double _height;
    /**
     * width of view plane
     */
    private double _width;
    /**
     * camera distance from view plane
     */
    private double _distance;

    /**
     * Builds camera according to location of source point and vectors that define view plane
     *
     * @param location point where the camera is located
     * @param vto      z-axis to view plane
     */
    public Camera(Point location, Vector vto, Vector vup) {
        if (!isZero(vto.dotProduct(vup)))
            throw new IllegalArgumentException("Vectors must be orthogonal");

        _location = location;
        _vto = vto.normalize();
        _vup = vup.normalize();
        _vright = vto.crossProduct(vup).normalize();
    }

    /**
     * Builder pattern - sets property and return the object
     *
     * @param width  width of view plane
     * @param height height of view plane
     * @return this
     */
    public Camera setVPSize(double width, double height) {
        _width = width;
        _height = height;
        return this;
    }

    /**
     * Builder pattern - sets property and return the object
     *
     * @param distance distance of camera from the vew plane
     * @return this
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }

    /***
     * Constructs ray through pixel
     * @param nX columns
     * @param nY rows
     * @param j pixel column
     * @param i pixel row
     * @return Ray starting at camera that goes through the pixel
     */
    public Ray constructRay(int nX, int nY, int j, int i) {
        double ry = _height / nY;
        double rx = _width / nX;
        double yScale = alignZero((j - nX / 2d) * rx + rx / 2d);
        double xScale = alignZero((i - nY / 2d) * ry + ry / 2d);
        Point p = _location.add(_vto.scale(_distance)); // center
        if (!isZero(yScale))
            p = p.add(_vright.scale(yScale));
        if (!isZero(xScale))
            p = p.add(_vup.scale(-1 * xScale));
        Vector v = p.subtract(_location);
        v = v.normalize();
        return new Ray(_location, p.subtract(_location));
    }
}
