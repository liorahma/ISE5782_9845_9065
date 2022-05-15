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

    @Test
    public void picture() {
        Camera camera = new Camera(new Point(-1500, 0, 100), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setVPSize(150, 150).setVPDistance(1000);
        Polygon lane = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(1500, 100, 0), new Point(1500, -100, 0), new Point(-500, -100, 0)).setEmission(new Color(PINK))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolL = new Polygon(new Point(-500, 100, 0), new Point(1500, 100, 0), new Point(1500, 120, -20), new Point(-500, 120, -20));
        Polygon rightPolR = new Polygon(new Point(-500, 140, 0), new Point(1500, 140, 0), new Point(1500, 120, -20), new Point(-500, 120, -20));
        Polygon leftPolL = new Polygon(new Point(-500, -140, 0), new Point(1500, -140, 0), new Point(1500, -120, -20), new Point(-500, -120, -20));
        Polygon leftPolR = new Polygon(new Point(-500, -100, 0), new Point(1500, -100, 0), new Point(1500, -120, -20), new Point(-500, -120, -20));
        Material bowlMat = new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19);
        BowlingPin bowlingPin = new BowlingPin(new Ray(new Point(1000, 0, 0), new Vector(0, 0, 1)), bowlMat, new Color(WHITE), new Color(RED));
        _scene._geometries.add(bowlingPin, lane);


        camera.setImageWriter(new ImageWriter("ourPictureMP1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }
}
