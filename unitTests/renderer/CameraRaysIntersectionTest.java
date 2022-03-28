package renderer;

import geometries.Intersectable;
import org.junit.jupiter.api.Test;
import primitives.*;
import geometries.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;




/***
 * Testing rays intersections with geometries on view plane
 * Integration test methods for {@link Camera#constructRay(int, int, int, int)}
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class CameraRaysIntersectionTest {

    /**
     * integration with {@link Triangle#findIntersections(Ray)}
     */
    @Test
    public void testCameraRayTriangleIntersections() {
        String bad = "Bad number of rays";
        Camera camera = new Camera(new Point(0, 0, 4),
                new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(10d)
                .setVPSize(6d, 6d);
        // TC01: Triangle in front of view plane and parallel to it, small triangle (1 point)
        Triangle triangle = new Triangle(new Point(0.5, 0.5, 1),
                new Point(-1, 0.5, 1),
                new Point(0.5, -1, 1));
        assertEquals(1, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC02: Triangle behind view plane and parallel to it, small triangle (1 point)
        triangle = new Triangle(new Point(0.5, 0.5, -1),
                new Point(-1, 0.5, -1),
                new Point(0.5, -1, -1));
        assertEquals(1, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC03: camera ray is included in triangle's plane (0 point)
        triangle = new Triangle(new Point(0, 0, 0),
                new Point(0, 0, 1),
                new Point(0, 1, 1));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC04: Triangle is not parallel to view plane and goes through it, small triangle (1 point)
        triangle = new Triangle(new Point(0.5, 0.5, 1),
                new Point(-1, 0.5, -1),
                new Point(0.5, -1, -1));
        assertEquals(1, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC05: Triangle is not parallel to view plane and not visible (0 points)
        triangle = new Triangle(new Point(20.5, 20.5, 11),
                new Point(20, 20, 10),
                new Point(19, 19, 11));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC06: Triangle is parallel to view plane and behind camera (0 points)
        triangle = new Triangle(new Point(0.5, 0.5, 11),
                new Point(-1, 0.5, 11),
                new Point(0.5, -1, 11));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, triangle), bad);
        // TC07: Triangle is included in view plane (9 points)
        triangle = new Triangle(new Point(0, -20, 0),
                new Point(-20, 20, 0),
                new Point(20, 20, 0));
        assertEquals(9, findCameraRaysIntersections(camera, 3, 3, triangle), bad);


    }

    /**
     * integration with {@link Plane#findIntersections(Ray)}
     */
    @Test
    public void testCameraRayPlaneIntersections() {
        String bad = "Bad number of rays";
        Camera camera = new Camera(new Point(0, 0, 4),
                new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(4d)
                .setVPSize(3d, 3d);
        // TC01: Plane is behinds camera (0 points)
        Plane plane = new Plane(new Point(0, 0, 5), new Vector(0, 0, 1));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC02: Plane is between camera and view plane and parallel to view plane (9 points)
        plane = new Plane(new Point(0, 0, 2), new Vector(0, 0, 1));
        assertEquals(9, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC03: Camera ray is included in plane (0 points)
        plane = new Plane(new Point(0, 0, 5), new Vector(0, 1, 0));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC04: Plane is not parallel to view plane and goes through it (9 points)
        plane = new Plane(new Point(0, 0, 0), new Vector(1, 1, 1));
        assertEquals(9, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC04: Plane is not parallel to view plane and does not go through it (0 points)
        plane = new Plane(new Point(10, 10, 0), new Vector(0, 1, 1));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC06: Plane is parallel to view plane and is behind it (0 points)
        plane = new Plane(new Point(0, 0, 10), new Vector(0, 0, 1));
        assertEquals(0, findCameraRaysIntersections(camera, 3, 3, plane), bad);
        // TC07: view plane is included in plane (9 points)
        plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
        assertEquals(9, findCameraRaysIntersections(camera, 3, 3, plane), bad);
    }

    /**
     * integration with {@link Sphere#findIntersections(Ray)}
     */
    @Test
    public void testCameraRaySphereIntersections() {
        Camera camera = new Camera(new Point(0, 0, 4),
                new Vector(0, 0, -1), new Vector(0, 1, 0))
                .setVPDistance(4d)
                .setVPSize(4d, 4d);
        // *** Group: Sphere is completely visible
        // TC01: Sphere behind view plane and visible completely from the view plane (2 points)
        Sphere sphere = new Sphere(new Point(0.5, 0.5, -1), 0.5);
        assertEquals(2, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - visible sphere after plane");
        // TC02: Sphere in front of view plane and visible completely (2 points)
        sphere = new Sphere(new Point(0.5, 0.5, 1), 0.5);
        assertEquals(2, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - visible sphere before plane");

        // *** Group: view plane is in the sphere, sphere is after camera
        // TC03: Sphere boundaries are inside view plane (8 points)
        sphere = new Sphere(new Point(0, 0, 0), 1);
        assertEquals(8, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - view plane partially inside sphere");
        // TC04: Sphere boundaries are outside view plane (16 points)
        sphere = new Sphere(new Point(0, 0, 0), 3.9d);
        assertEquals(32, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - view plane completely inside sphere");

        // TC05: Sphere boundaries are outside view plane, camera and vp are inside the sphere (16 points)
        sphere = new Sphere(new Point(0, 0, 0), 5);
        assertEquals(16, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - view plane and camera completely inside sphere");

        // TC06: camera and view plane are behind the sphere (0 points)
        sphere = new Sphere(new Point(0, 0, 10), 1);
        assertEquals(0, findCameraRaysIntersections(camera, 4, 4, sphere),
                "Wrong number - view plane and camera are behind the sphere");
    }

    /**
     * finds number of intersections that camera rays have with a given geometry
     *
     * @param camera   camera to check
     * @param nX       number of columns
     * @param nY       number of rows
     * @param geometry geometry to check
     * @return number of times camera rays intersect geometry
     */
    private int findCameraRaysIntersections(Camera camera, int nX, int nY, Intersectable geometry) {
        int numOfIntersections = 0;
        for (int i = 0; i < nX; i++) {
            for (int j = 0; j < nY; j++) {
                List<Point> intersections = geometry.findIntersections(camera.constructRay(nX, nY, j, i));
                if (intersections != null)
                    numOfIntersections += intersections.size();
            }
        }
        return numOfIntersections;
    }
}
