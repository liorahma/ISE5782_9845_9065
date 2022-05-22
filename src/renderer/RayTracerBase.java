package renderer;

import primitives.*;
import scene.Scene;

/**
 * Class representing a base for a Ray tracer
 */
public abstract class RayTracerBase {

    /**
     * scene to trace rays in
     */
    protected Scene _scene;

    public RayTracerBase(Scene scene){
        _scene = scene;
    }

    /**
     * Method that traces a ray and returns the color that the pixel should be colored in
     * @param ray ray to be traced and colored
     * @return Color for the pixel of a certain ray
     */
    public abstract Color traceRay(Ray ray);
}
