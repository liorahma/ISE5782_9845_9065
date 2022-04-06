package renderer;

import jdk.jshell.spi.ExecutionControl;
import primitives.*;
import scene.Scene;

import java.util.List;
import java.util.Scanner;

/**
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class RayTracerBasic extends RayTracerBase {

    public RayTracerBasic(Scene scene) {
        super(scene);
        //throw new ExecutionControl.NotImplementedException("RayTracerBasic was not yet implemented");
    }

    @Override
    public Color traceRay(Ray ray) {
        List<Point> points = _scene._geometries.findIntersections(ray);
        if (points == null)
            return _scene._background;
        Point closest = ray.findClosestPoint(points);
        return calcColor(closest);
    }
    private Color calcColor(Point point){
        return _scene._ambientLight.getIntensity();
    }
}
