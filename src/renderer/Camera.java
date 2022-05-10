package renderer;

import static primitives.Util.*;

import primitives.*;

import java.util.MissingResourceException;

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
     * Image writer to write the image
     */
    private ImageWriter _imageWriter;
    /**
     * ray tracer to trace ray and store the scene
     */
    private RayTracerBase _rayTracerBase;

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
     * @param distance distance of camera from the view plane
     * @return this
     */
    public Camera setVPDistance(double distance) {
        _distance = distance;
        return this;
    }


    /**
     * Builder pattern - sets property and return the object
     *
     * @param imageWriter image writer
     * @return this
     */
    public Camera setImageWriter(ImageWriter imageWriter) {
        _imageWriter = imageWriter;
        return this;
    }

    /**
     * Builder pattern - sets property and return the object
     *
     * @param rayTracerBase ray tracer object
     * @return this
     */
    public Camera setRayTracer(RayTracerBase rayTracerBase) {
        _rayTracerBase = rayTracerBase;
        return this;
    }

    /**
     * allows moving camera's location according to parameters in each axis
     *
     * @param to    change in to axis
     * @param up    change in up axis
     * @param right change in right axis
     * @return this
     */
    public Camera move(double to, double up, double right) {
        if (!isZero(to)) _location = _location.add(_vto.scale(to));
        if (!isZero(up)) _location = _location.add(_vup.scale(up));
        if (!isZero(right)) _location = _location.add(_vright.scale(right));
        return this;
    }

    /**
     * allows rotating camera on a given axis by a given angle
     * @param rotationAxis the rotation axis
     * @param angle the rotation angle
     * @return this
     */
    public Camera rotate(Vector rotationAxis, double angle) {
        if (isZero(angle)) return this;
        _vto = _vto.rotate(rotationAxis, angle);
        _vup = _vup.rotate(rotationAxis, angle);
        _vright = _vright.rotate(rotationAxis, angle);
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

    /**
     * render the image using the image writer
     */
    public Camera renderImage() {
        if (_location == null)
            throw new MissingResourceException("location is missing", "Point", "location");
        if (_vto == null)
            throw new MissingResourceException("Vto is missing", "Vector", "vto");
        if (_vup == null)
            throw new MissingResourceException("Vup is missing", "Vector", "vup");
        if (_vright == null)
            throw new MissingResourceException("Vright is missing", "Vector", "vright");
        if (_height == 0)
            throw new MissingResourceException("height is missing", "double", "height");
        if (_width == 0)
            throw new MissingResourceException("width is missing", "double", "width");
        if (_distance == 0)
            throw new MissingResourceException("distance is missing", "double", "distance");
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter is missing", "ImageWriter", "imageWriter");
        if (_rayTracerBase == null)
            throw new MissingResourceException("rayTracerBase is missing", "RayTraceBase", "rayTracerBase");

        // for each pixel
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                _imageWriter.writePixel(j, i, castRay(j, i));
            }
        }
        return this;
    }

    /**
     * Casts a ray through a pixel, traces it and returns the color for the pixel
     *
     * @param j col index
     * @param i row index
     * @return Color for a certain pixel
     */
    private Color castRay(int j, int i) {
        Ray ray = constructRay(_imageWriter.getNx(), _imageWriter.getNy(), j, i);
        return _rayTracerBase.traceRay(ray);
    }


    /**
     * Creates grid according to the interval defined
     *
     * @param interval width and length of a grid square (in pixels)
     * @param color    color of grid
     */
    public void printGrid(int interval, Color color) {
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter is missing", "ImageWriter", "imageWriter");
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                // on grid
                if (i % interval == 0 || j % interval == 0)
                    _imageWriter.writePixel(j, i, color);
            }
        }
    }

    /**
     * Saves the image as a .png file
     */
    public void writeToImage() {
        if (_imageWriter == null)
            throw new MissingResourceException("imageWriter is missing", "ImageWriter", "imageWriter");
        _imageWriter.writeToImage();
    }

}
