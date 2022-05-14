package finalPicture;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

public class OurPictureMP1 {
    private Scene _scene = new Scene("Test scene");

    public void picture() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(150, 150).setVPDistance(1000);
        Polygon lane = new Polygon(new Point(-500, 100, 0), new Point(1500, 100, 0), new Point(1500, -100, 0), new Point(-500, -100, 0));
        Polygon rightPolL = new Polygon(new Point(-500, 100, 0), new Point(1500, 100, 0), new Point(1500, 120, -20), new Point(-500, 120, -20));
        Polygon rightPolR = new Polygon(new Point(-500, 140, 0), new Point(1500, 140, 0), new Point(1500, 120, -20), new Point(-500, 120, -20));
        Polygon leftPolL = new Polygon(new Point(-500, -140, 0), new Point(1500, -140, 0), new Point(1500, -120, -20), new Point(-500, -120, -20));
        Polygon leftPolR = new Polygon(new Point(-500, -100, 0), new Point(1500, -100, 0), new Point(1500, -120, -20), new Point(-500, -120, -20));

        camera.setImageWriter(new ImageWriter("ourPictureMP1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }
}
