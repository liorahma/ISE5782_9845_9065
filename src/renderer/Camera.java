package renderer;

import static primitives.Util.*;

import primitives.*;

import java.util.LinkedList;
import java.util.List;
import java.util.MissingResourceException;
import java.util.Random;

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
     * number of rays in beam for supersampling
     */
    private int _nSS = 64;

    /**
     * maximum level of recursion for adaptive supersampling
     */
    private int _maxLevelAdaptiveSS = 3;

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
     * setter for _nSS
     *
     * @param nSS value
     * @return this
     */
    public Camera setNSS(int nSS) {
        _nSS = nSS;
        return this;
    }

    /**
     * setter for _maxLevel
     *
     * @param maxLevelAdaptiveSS value
     * @return this
     */
    public Camera setMaxLevelAdaptiveSS(int maxLevelAdaptiveSS) {
        _maxLevelAdaptiveSS = maxLevelAdaptiveSS;
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
     *
     * @param rotationAxis the rotation axis
     * @param angle        the rotation angle
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
     * function checking that camera object has everything needed for rendering
     */
    private void checkExceptions() {
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

    }

    /**
     * render the image using the image writer
     */
    public Camera renderImage() {
        checkExceptions();
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
     * render the image using the image writer, using super sampling in the random method
     */
    public Camera renderImageSuperSampling() {
        checkExceptions();
        // for each pixel
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                _imageWriter.writePixel(j, i, castBeamSuperSampling(j, i));
            }
        }
        return this;
    }

    /**
     * casts beam of rays around the center ray of pixel
     *
     * @param j col index
     * @param i row index
     * @return Color for a certain pixel
     */
    private Color castBeamSuperSampling(int j, int i) {
        List<Ray> beam = constructBeamSuperSampling(_imageWriter.getNx(), _imageWriter.getNy(), j, i);
        Color color = Color.BLACK;
        // calculate average color of rays traced
        for (Ray ray : beam) {
            color = color.add(_rayTracerBase.traceRay(ray));
        }
        return color.reduce(_nSS);
    }

    /**
     * creates beam of rays around center of pixel randomly
     *
     * @param nX num of row pixels
     * @param nY num of col pixels
     * @param j  col index
     * @param i  row index
     * @return list of rays in beam
     */
    private List<Ray> constructBeamSuperSampling(int nX, int nY, int j, int i) {
        List<Ray> beam = new LinkedList<>();
        beam.add(constructRay(nX, nY, j, i));
        double ry = _height / nY;
        double rx = _width / nX;
        double yScale = alignZero((j - nX / 2d) * rx + rx / 2d);
        double xScale = alignZero((i - nY / 2d) * ry + ry / 2d);
        Point pixelCenter = _location.add(_vto.scale(_distance)); // center
        if (!isZero(yScale))
            pixelCenter = pixelCenter.add(_vright.scale(yScale));
        if (!isZero(xScale))
            pixelCenter = pixelCenter.add(_vup.scale(-1 * xScale));
        Random rand = new Random();
        // create rays randomly around the center ray
        for (int c = 0; c < _nSS; c++) {
            // move randomly in the pixel
            double dxfactor = rand.nextBoolean() ? rand.nextDouble() : -1 * rand.nextDouble();
            double dyfactor = rand.nextBoolean() ? rand.nextDouble() : -1 * rand.nextDouble();
            double dx = rx * dxfactor;
            double dy = ry * dyfactor;
            Point randomPoint = pixelCenter;
            if (!isZero(dx))
                randomPoint = randomPoint.add(_vright.scale(dx));
            if (!isZero(dy))
                randomPoint = randomPoint.add(_vup.scale(-1 * dy));
            beam.add(new Ray(_location, randomPoint.subtract(_location)));
        }
        return beam;
    }

    /**
     * render the image using the image writer, using adaptive supersampling
     */
    public Camera renderImageAdaptiveSuperSampling() {
        checkExceptions();
        // for each pixel
        for (int i = 0; i < _imageWriter.getNx(); i++) {
            for (int j = 0; j < _imageWriter.getNy(); j++) {
                _imageWriter.writePixel(j, i, castBeamAdaptiveSuperSampling(j, i));
            }
        }
        return this;
    }

    /**
     * casts beam of rays in pixel according to adaptive supersampling
     *
     * @param j col index
     * @param i row index
     * @return Color for a certain pixel
     */
    private Color castBeamAdaptiveSuperSampling(int j, int i) {
        Ray center = constructRay(_imageWriter.getNx(), _imageWriter.getNy(), j, i);
        Color centerColor = _rayTracerBase.traceRay(center);
        return calcAdaptiveSuperSampling(_imageWriter.getNx(), _imageWriter.getNy(), j, i, _maxLevelAdaptiveSS, centerColor);
    }

    /**
     * calculates actual color using adaptive supersampling
     *
     * @param nX    num of rows
     * @param nY    num of cols
     * @param j     col index of pixel
     * @param i     row index of pixel
     * @param level level of recursion
     * @return color of pixel
     */
    private Color calcAdaptiveSuperSampling(int nX, int nY, int j, int i, int level, Color centerColor) {
        //Ray center = constructRay(nX, nY, j, i);
        // recursion reached maximum level
        if (level == 0) {
            //return _rayTracerBase.traceRay(center);
            return centerColor;
        }
        Color color = centerColor;
        //Color centerColor = _rayTracerBase.traceRay(center);
        // divide pixel into 4 mini-pixels
        Ray[] beam = new Ray[]{constructRay(2 * nX, 2 * nY, 2 * j, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j, 2 * i + 1),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i),
                constructRay(2 * nX, 2 * nY, 2 * j + 1, 2 * i + 1)};
        // for each mini-pixel
        for (int ray = 0; ray < 4; ray++) {
            Color currentColor = _rayTracerBase.traceRay(beam[ray]);
            if (!currentColor.equals(centerColor))
                currentColor = calcAdaptiveSuperSampling(2 * nX, 2 * nY,
                        2 * j + ray / 2, 2 * i + ray % 2, level - 1, currentColor);
            color = color.add(currentColor);
        }
        return color.reduce(5);
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
