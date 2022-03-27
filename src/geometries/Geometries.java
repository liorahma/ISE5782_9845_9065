package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent a list of Geometric objects
 */
public class Geometries implements Intersectable {

    private List<Intersectable> _geometries;

    public Geometries() {
        _geometries = new ArrayList<>();
    }

    /**
     * creates a list of Geometric objects provided
     *
     * @param geometries objects to add to list of geometries
     */
    public Geometries(Intersectable... geometries) {
        _geometries = new ArrayList<>();
        for (Intersectable i : geometries) {
            _geometries.add(i);
        }
    }

    /**
     * adds provided objects to the list of geometries
     *
     * @param geometries objects to add to list of geometries
     */
    public void add(Intersectable... geometries) {
        for (Intersectable i : geometries) {
            _geometries.add(i);
        }
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        if (_geometries == null)
            return null;
        List<Point> ans = new ArrayList<>();
        List<Point> tmp;
        //for each shape, adds its intersection points to list of intersection points
        for (Intersectable i : _geometries) {
            tmp = i.findIntersections(ray);
            if (tmp != null)
                ans.addAll(tmp);
        }
        if (ans.isEmpty())
            return null;
        return ans;
    }
}
