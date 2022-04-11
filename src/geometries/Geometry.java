package geometries;

import primitives.*;

/**
 * abstract class Geometry - represents any kind of 3D geometry
 */
public abstract class Geometry extends Intersectable {

    protected Color _emission=Color.BLACK;
    /**
     * getter for emission
     * @return emission variable
     */
    public Color getEmission() {
        return _emission;
    }
    /**
     * setter for emission
     * @return this
     */
    public Geometry setEmission(Color _emission) {
        this._emission = _emission;
        return this;
    }
    /**
     * Returns a normal vector to current geometry
     * @param point point from which a normal vector is requested
     * @return a normal vector
     */
    public abstract Vector getNormal(Point point);
}
