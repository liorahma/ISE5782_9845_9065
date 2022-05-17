package improvements;


import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

/**
 * @author Liorah Mandelbaum and Sarah Bednarsh
 * test class for super sampling
 */
public class SuperSampling {

    /**
     * Test method for {@link Camera#renderImageSuperSampling()}
     */
    @Test
    public void testSuperSamplingRandom() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
                        new Double3(1, 1, 1))) //
                .setBackground(new Color(75, 127, 90));

        scene._geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("basicTestSSRandomBefore", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
        camera.setImageWriter(new ImageWriter("basicTestSSRandomAfter", 1000, 1000));
        camera.renderImageSuperSampling();
        camera.writeToImage();
    }

    /**
     * Test method for {@link Camera#renderImageAdaptiveSuperSampling()}
     */
    @Test
    public void testAdaptiveSuperSampling() {
        Scene scene = new Scene("Test scene")//
                .setAmbientLight(new AmbientLight(new Color(255, 191, 191), //
                        new Double3(1, 1, 1))) //
                .setBackground(new Color(75, 127, 90));

        scene._geometries.add(new Sphere(new Point(0, 0, -100), 50d),
                new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100)), // up
                // left
                new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100)), // down
                // left
                new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
        // right
        Camera camera = new Camera(Point.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPDistance(100) //
                .setVPSize(500, 500) //
                .setImageWriter(new ImageWriter("basicTestAdaptiveSSBefore", 1000, 1000))
                .setRayTracer(new RayTracerBasic(scene));
        camera.renderImage();
        camera.printGrid(100, new Color(YELLOW));
        camera.writeToImage();
        camera.setImageWriter(new ImageWriter("basicTestAdaptiveSSAfter", 1000, 1000));
        camera.renderImageAdaptiveSuperSampling();
        camera.writeToImage();
    }

}
