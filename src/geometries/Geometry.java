package geometries;

import primitives.*;

/**
 * abstract class Geometry - represents any kind of 3D geometry
 */
public abstract class Geometry extends Intersectable {

    /**
     * emission light color
     */
    protected Color _emission = Color.BLACK;
    /**
     * material of geometrical object
     */
    private Material _material = new Material();

    /**
     * getter for emission
     * @return emission variable
     */
    public Color getEmission() {
        return _emission;
    }

    /**
     * setter for emission, builder pattern
     * @param emission color to be set in emission color
     * @return this
     */
    public Geometry setEmission(Color emission) {
        this._emission = emission;
        return this;
    }

    /**
     * getter for material of geometry
     * @return material of geometry
     */
    public Material getMaterial() {
        return _material;
    }

    /**
     * setter for material, builder pattern
     *
     * @param material material of geometry
     * @return this
     */
    public Geometry setMaterial(Material material) {
        _material = material;
        return this;
    }

    /**
     * Returns a normal vector to current geometry
     *
     * @param point point from which a normal vector is requested
     * @return a normal vector
     */
    public abstract Vector getNormal(Point point);
}
