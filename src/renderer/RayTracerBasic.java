package renderer;

import primitives.*;
import scene.Scene;

import java.util.List;

import geometries.Intersectable.GeoPoint;
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
        List<GeoPoint> points = _scene._geometries.findGeoIntersections(ray);
        if (points == null)
            return _scene._background;
        GeoPoint closest = ray.findClosestGeoPoint(points);
        return calcColor(closest);
    }
    private Color calcColor(GeoPoint point){
        return _scene._ambientLight.getIntensity().add(point._geometry.getEmission());
    }
}
