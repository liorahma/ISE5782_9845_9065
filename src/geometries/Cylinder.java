package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.ArrayList;
import java.util.List;

import static primitives.Util.*;

/**
 * class Cylinder represents a 3D cylinder
 */
public class Cylinder extends Tube {
    private double _height;

    /**
     * constructs a cylinder with Ray, radius and height
     *
     * @param axisRay Ray for axis
     * @param radius  radius for base
     * @param height  height of cylinder
     */
    public Cylinder(Ray axisRay, double radius, double height) {
        super(axisRay, radius);
        this._height = height;
    }


    public double getHeight() {
        return _height;
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "height=" + _height +
                '}';
    }

    /**
     * returns normal vector from give point
     *
     * @param point from which we start the vector
     * @return normal vector
     */
    @Override
    public Vector getNormal(Point point) {
        // Vector point - p0
        Vector p0point = point.subtract(_axisRay.getP0());
        // Vector point - (p0 + height * dir)
        Vector p0HeightPoint = point.subtract(_axisRay.getP0().add(_axisRay.getDir().scale(_height)));
        // if point is on one of the bases
        if (isZero(p0point.dotProduct(_axisRay.getDir()))
                || isZero(p0HeightPoint.dotProduct(_axisRay.getDir()))) {
            return _axisRay.getDir();
        }
        return super.getNormal(point);
    }

    /**
     * returns list of intersection points between given ray and our geometry
     *
     * @param ray that we check if it intersects
     * @return list of intersections
     */
    @Override
    public List<Point> findIntersections(Ray ray) {
//        List<Point> points = super.findIntersections(ray);
//        if (points == null)
//            return findIntersectionsTops(ray);
//
//        if (points != null) {
//            Point p = points.get(0);
//            Vector v = p.subtract(_axisRay.getP0());
//        }
        Plane base1 = new Plane(_axisRay.getP0(), _axisRay.getDir());
        Plane base2 = new Plane(_axisRay.getPoint(_height), _axisRay.getDir());
        Vector vAxis = _axisRay.getDir();
        Vector v = ray.getDir();
        Point p0 = ray.getP0();
        Point pC = _axisRay.getP0();
        Point p1;
        Point p2;

        // intersections of the ray with infinite cylinder {without the bases)
        List<Point> intersections = super.findIntersections(ray);
        double vAxisV = alignZero(vAxis.dotProduct(v)); // cos(angle between ray directions)

        if (intersections == null) { // no intersections with the infinite cylinder
            try {
                vAxis.crossProduct(v); // if ray and axis are parallel - throw exception
                return null; // they are not parallel - the ray is outside the cylinder
            } catch (Exception e) {
            }

            // The rays are parallel
            Vector vP0PC;
            try {
                vP0PC = pC.subtract(p0); // vector from P0 to Pc (O1)
            } catch (Exception e) { // the rays start at the same point
                // check whether the ray goes into the cylinder
                return vAxisV > 0 ? //
                        List.of(ray.getPoint(_height)) : null;
            }

            double t1 = alignZero(vP0PC.dotProduct(v)); // project Pc (O1) on the ray
            p1 = ray.getPoint(t1); // intersection point with base1

            // Check the distance between the rays
            if (alignZero(p1.distance(pC) - _radius) >= 0)
                return null;

            // intersection points with base2
            double t2 = alignZero(vAxisV > 0 ? t1 + _height : t1 - _height);
            p2 = ray.getPoint(t2);
            // the ray goes through the bases - test bases vs. ray head and return points
            // accordingly
            if (t1 > 0 && t2 > 0)
                return List.of(p1, p2);
            if (t1 > 0)
                return List.of(p1);
            if (t2 > 0)
                return List.of(p2);
            return null;
        }

        if (intersections.size() == 2) {
            // Ray - infinite cylinder intersection points
            p1 = intersections.get(0);
            p2 = intersections.get(1);

            // get projection of the points on the axis vs. base1 and update the
            // points if both under base1 or they are from different sides of base1
            double p1OnAxis = alignZero(p1.subtract(pC).dotProduct(vAxis));
            double p2OnAxis = alignZero(p2.subtract(pC).dotProduct(vAxis));
            if (p1OnAxis < 0 && p2OnAxis < 0)
                p1 = null;
            else if (p1OnAxis < 0)
                p1 = base1.findIntersections(ray).get(0);
            else
                /* p2OnAxis < 0 */ p2 = base1.findIntersections(ray).get(0);

            // get projection of the points on the axis vs. base2 and update the
            // points if both above base2 or they are from different sides of base2
            double p1OnAxisMinusH = alignZero(p1OnAxis - _height);
            double p2OnAxisMinusH = alignZero(p2OnAxis - _height);
            if (p1OnAxisMinusH > 0 && p2OnAxisMinusH > 0)
                p1 = null;
            else if (p1OnAxisMinusH > 0)
                p1 = base2.findIntersections(ray).get(0);
            else
                /* p2OnAxisMinusH > 0 */ p2 = base2.findIntersections(ray).get(0);

            // Check the points and return list of points accordingly
            if (p1 != null && p2 != null)
                return List.of(p1, p2);
            if (p1 != null)
                return List.of(p1);
            if (p2 != null)
                return List.of(p2);
            return null;
        } else//one intersection- starts inside the tube
        {
            p1 = intersections.get(0);
            //todooooooooooooooo
        }
        return null;

    }

//    private List<Point> findIntersectionsTops(Ray ray) {
//        return null;
//    }

    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        return null;
    }
}
//// Ray - infinite cylinder intersection points
//        p1 = intersections.get(0);
//                if (intersections.size() == 2)
//                p2 = intersections.get(1);
//                else p2 = null;
//
//                // get projection of the points on the axis vs. base1 and update the
//                // points if both under base1, or they are from different sides of base1
//                double p1OnAxis = alignZero(p1.subtract(pC).dotProduct(vAxis));
//                double p2OnAxis = 0;//just assigned so there is no trouble
//                if (p2 != null) {
//                p2OnAxis = alignZero(p2.subtract(pC).dotProduct(vAxis));
//                if (p1OnAxis < 0 && p2OnAxis < 0)
//        p1 = null;
//        else if (p1OnAxis < 0)
//        p1 = base1.findIntersections(ray).get(0);
//        else if(p2OnAxis < 0)
//        /* p2OnAxis < 0 */ p2 = base1.findIntersections(ray).get(0);
//        }
//
//        // get projection of the points on the axis vs. base2 and update the
//        // points if both above base2, or they are from different sides of base2
//        double p1OnAxisMinusH = alignZero(p1OnAxis - _height);
//        double p2OnAxisMinusH = 0;//just assigned so there is no trouble
//        if (p2 != null)
//        p2OnAxisMinusH = alignZero(p2OnAxis - _height);
//        if (p2 != null)
//        if (p1OnAxisMinusH > 0 && p2OnAxisMinusH > 0)
//        p1 = null;
//        if (p1OnAxisMinusH > 0)
//        p1 = base2.findIntersections(ray).get(0);
//        if(p2!=null)
//        if (p2OnAxisMinusH > 0)
//        /* p2OnAxisMinusH > 0 */ p2 = base2.findIntersections(ray).get(0);
//
//        // Check the points and return list of points accordingly
//        if (p1 != null && p2 != null)
//        return List.of(p1, p2);
//        if (p1 != null)
//        return List.of(p1);
//        if (p2 != null)
//        return List.of(p2);
//        return null;