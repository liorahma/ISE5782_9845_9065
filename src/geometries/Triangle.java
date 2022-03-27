package geometries;

import primitives.*;

import static primitives.Util.*;

import java.util.ArrayList;
import java.util.List;

public class Triangle extends Polygon {
    public Triangle(Point p1, Point p2, Point p3) {
        super(p1, p2, p3);
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "vertices=" + _vertices +
                ", plane=" + _plane +
                '}';
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        List<Point> intersections = _plane.findIntersections(ray);
        if (intersections==null)
            return null;
        if (!isInside(intersections.get(0))) {
            return null;
        }
        return intersections;
    }

    private boolean isInside(Point p) {
        Point a = this._vertices.get(0);
        Point b = this._vertices.get(1);
        Point c = this._vertices.get(2);
        // if intersection point is one of the edges
        if (p.equals(a) || p.equals(b) || p.equals(c))
            return false;

        Vector normal = _plane.getNormal();

        // check edge1
        Vector edge1 = b.subtract(a);
        Vector toP1 = p.subtract(a);

        Vector orthogonal1;
        try {
            orthogonal1 = edge1.crossProduct(toP1);
        } catch (IllegalArgumentException e) {
            // if an exception about zero vector was throw, point is on the edge
            return false;
        }
        // if resulting normal does not have the same direction as original normal, point is outside
        if (alignZero(normal.dotProduct(orthogonal1)) < 0)
            return false;
        // check edge2
        Vector edge2 = c.subtract(b);
        Vector toP2 = p.subtract(b);
        Vector orthogonal2;
        try {
            // if an exception about zero vector was throw, point is on the edge
            orthogonal2 = edge2.crossProduct(toP2);
        } catch (IllegalArgumentException e) {
            return false;
        }
        // if resulting normal does not have the same direction as original normal, point is outside
        if (alignZero(normal.dotProduct(orthogonal2)) < 0)
            return false;
        // check edge3
        Vector edge3 = a.subtract(c);
        Vector toP3 = p.subtract(c);
        Vector orthogonal3;
        try {
            // if an exception about zero vector was throw, point is on the edge
            orthogonal3 = edge3.crossProduct(toP3);
        } catch (IllegalArgumentException e) {
            return false;
        }
        // if resulting normal does not have the same direction as original normal, point is outside
        if (alignZero(normal.dotProduct(orthogonal3)) < 0)
            return false;

        return true;
    }
}
