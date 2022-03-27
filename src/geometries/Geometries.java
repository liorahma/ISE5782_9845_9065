package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Geometries implements Intersectable{

    private List<Intersectable> _geometries;

    public Geometries()    {
        _geometries= new ArrayList<>();
    }
    public Geometries(Intersectable... geometries)    {
        _geometries= new ArrayList<>();
        for (Intersectable i:geometries) {
            _geometries.add(i);
        }
    }

    public void add(Intersectable... geometries){
        for (Intersectable i:geometries) {
            _geometries.add(i);
        }
    }
    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
