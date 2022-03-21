package geometries;

import primitives.*;

/**
 * interface Geometry - represents any kind of 3D geometry
 */
public interface Geometry extends Intersectable {
    /**
     * Returns a normal vector to current geometry
     * @param point point from which a normal vector is requested
     * @return a normal vector
     */
    public Vector getNormal(Point point);
}
