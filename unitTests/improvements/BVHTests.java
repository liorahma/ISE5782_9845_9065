package improvements;

import geometries.*;
import lighting.AmbientLight;
import lighting.PointLight;
import lighting.SpotLight;
import org.junit.jupiter.api.Test;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import scene.Scene;

import static java.awt.Color.*;

public class BVHTests {


    private Scene _scene = new Scene("Test scene");

    @Test
    public void tryNoBVH() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(400, 400).setVPDistance(1000);

        Geometry polyMirror = new Polygon(new Point(-150, -100, 0),
                new Point(-184.660049030367446, -39.577189086898301, 69.320098060734892),
                new Point(-178.588419505802932, 57.753112706325403, 57.176839011605836),
                new Point(-137.856740950870915, 94.660603586447394, -24.286518098258195),
                new Point(-103.196691920503469, 34.237792673345709, -93.606616158993106),
                new Point(-109.268321445067969, -63.092509119877974, -81.463357109864063))
                .setEmission(new Color(30, 30, 30))
                .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5));
        _scene._geometries.add( //
                polyMirror.setBvhIsOn(false),
                new Plane(new Point(0,0,-20),new Point(1,1,-20),new Point(-5,-7,-20)).setMaterial(new Material().setShininess(19).setKt(0.3).setKd(0.2).setKr(0.5).setKs(0.6).setKg(0.8)).setBvhIsOn(false),
                new Triangle(new Point(-80, 80, 30), new Point(-10, 5, 10), new Point(-90, 20, 40)) //
                        .setEmission(new Color(3,6,18)).setMaterial(new Material().setKd(0.4).setKt(0.5).setKs(0.5).setShininess(60)).setBvhIsOn(false),
                //new Triangle(new Point(100,100,0),new Point(-100,100,0), new Point(0,-90,-50))
                //new Polygon(new Point(100,100,0),new Point(-100,100,0), new Point(-100,0,0),new Point(0,-50,0), new Point(100,-100,0))
                //		.setMaterial(new Material().setKd(0.3).setKr(0.6).setKs(0.7).setShininess(30)),
                new Sphere(new Point(50,50,50),20).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(30).setKt(0.4)).setBvhIsOn(false));
        //_scene._lights.add(new SpotLight(new Color(700, 400, 400), new Point(100, 50, 0), new Vector(0, 0, -1)) //
        //		.setKl(4E-5).setKq(2E-7));
        _scene.setAmbientLight(new AmbientLight(new Color(WHITE),0.1));
        _scene._lights.add(new PointLight(new Color(LIGHT_GRAY),new Point(50,50,50)));
        _scene._lights.add( //
                new SpotLight(new Color(240, 193, 225), new Point(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));
        ImageWriter imageWriter = new ImageWriter("tryAllNoBVH", 600, 600);
        _scene._geometries.setBvhIsOn(false);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }


    @Test
    public void tryBVH() {
        Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
                .setVPSize(400, 400).setVPDistance(1000);

        Geometry polyMirror = new Polygon(new Point(-150, -100, 0),
                new Point(-184.660049030367446, -39.577189086898301, 69.320098060734892),
                new Point(-178.588419505802932, 57.753112706325403, 57.176839011605836),
                new Point(-137.856740950870915, 94.660603586447394, -24.286518098258195),
                new Point(-103.196691920503469, 34.237792673345709, -93.606616158993106),
                new Point(-109.268321445067969, -63.092509119877974, -81.463357109864063))
                .setEmission(new Color(30, 30, 30))
                .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5));
        _scene._geometries.add( //
                polyMirror,
                new Plane(new Point(0,0,-20),new Point(1,1,-20),new Point(-5,-7,-20)).setMaterial(new Material().setShininess(19).setKt(0.3).setKd(0.2).setKr(0.5).setKs(0.6).setKg(0.8)),
                new Triangle(new Point(-80, 80, 30), new Point(-10, 5, 10), new Point(-90, 20, 40)) //
                        .setEmission(new Color(3,6,18)).setMaterial(new Material().setKd(0.4).setKt(0.5).setKs(0.5).setShininess(60)),
                //new Triangle(new Point(100,100,0),new Point(-100,100,0), new Point(0,-90,-50))
                //new Polygon(new Point(100,100,0),new Point(-100,100,0), new Point(-100,0,0),new Point(0,-50,0), new Point(100,-100,0))
                //		.setMaterial(new Material().setKd(0.3).setKr(0.6).setKs(0.7).setShininess(30)),
                new Sphere(new Point(50,50,50),20).setEmission(new Color(BLUE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.2).setShininess(30).setKt(0.4)));
        //_scene._lights.add(new SpotLight(new Color(700, 400, 400), new Point(100, 50, 0), new Vector(0, 0, -1)) //
        //		.setKl(4E-5).setKq(2E-7));
        _scene.setAmbientLight(new AmbientLight(new Color(WHITE),0.1));
        _scene._lights.add(new PointLight(new Color(LIGHT_GRAY),new Point(50,50,50)));
        _scene._lights.add( //
                new SpotLight(new Color(240, 193, 225), new Point(-100, -100, 200), new Vector(1, 1, -3)) //
                        .setKl(1E-5).setKq(1.5E-7));
        ImageWriter imageWriter = new ImageWriter("tryAllBVH", 600, 600);
        camera.setImageWriter(imageWriter) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }
}

