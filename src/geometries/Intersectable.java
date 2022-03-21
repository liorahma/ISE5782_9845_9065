
package geometries;


import primitives.*;
import java.util.*;


/**
 * interface Intersectable - represents any kind of intersectable object
 */
public interface Intersectable {
    /**
     * finds intersections between ray and object
     * @param ray ray that intersects
     * @return list of intersection points
     */
    public List<Point> findIntersections(Ray ray);
}
