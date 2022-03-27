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
        if (_geometries==null)
            return null;
        List<Point> ans = new ArrayList<>();
        List<Point> tmp;
        for (Intersectable i:_geometries) {
            tmp= i.findIntersections(ray);
            if(tmp!=null)
                ans.addAll(tmp);
        }
        if(ans.isEmpty())
            return null;
        return ans;

    }
}
