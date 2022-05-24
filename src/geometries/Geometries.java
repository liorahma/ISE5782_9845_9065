package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class to represent a list of Geometric objects
 */
public class Geometries extends Intersectable {

    private List<Intersectable> _geometries;

    public Geometries() {
        _geometries = new LinkedList<>();
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
        if (_bvhIsOn)
            createBoundingBox();
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
        if (_bvhIsOn)
            createBoundingBox();
    }

    @Override
    public void createBoundingBox() {
        if (_geometries == null)
            return;
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (Intersectable geo : _geometries) {
            if (geo._box != null) {
                minX = Math.min(minX, geo._box._minimums.getX());
                minY = Math.min(minY, geo._box._minimums.getY());
                minZ = Math.min(minZ, geo._box._minimums.getZ());
                maxX = Math.max(maxX, geo._box._maximums.getX());
                maxY = Math.max(maxY, geo._box._maximums.getY());
                maxZ = Math.max(maxZ, geo._box._maximums.getZ());
            }
        }
        _box = new BoundingBox(new Point(minX, minY, minZ), new Point(maxX, maxY, maxZ));
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

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        if (_geometries == null)
            return null;
        List<GeoPoint> ans = new LinkedList<>();
        List<GeoPoint> tmp;
        // for each shape, adds its intersection points to list of intersection points
        for (Intersectable i : _geometries) {
            tmp = i.findGeoIntersections(ray, maxDistance);
            if (tmp != null)
                ans.addAll(tmp);
        }
        if (ans.isEmpty())
            return null;
        return ans;
    }
}
