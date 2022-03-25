package geometries;

import primitives.*;
import static primitives.Util.*;
import java.util.List;

public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + _vertices +
                ", plane=" + plane +
                '}';
    }
    @Override
    public List<Point> findIntersections(Ray ray) {

        return null;
    }
}
