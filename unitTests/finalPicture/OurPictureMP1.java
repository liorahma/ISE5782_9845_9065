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
        int lengthToX=20000;
        int firstX= 3000;
        int distanceBetweenPins=50;
        Color laneColor= new Color(255, 223, 128);
        Material bowlMat= new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19);
        Camera camera = new Camera(new Point(-1000, 0, 300), new Vector(1, 0, -0.05), new Vector(0.05, 0, 1)) //
                .setVPSize(200, 150).setVPDistance(1000);
        Camera cameraTop = new Camera(new Point(3000, 0, 1500), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
                .setVPSize(200, 150).setVPDistance(1000);
        Polygon lane = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, -100, 0), new Point(-500, -100, 0)).setEmission(laneColor)
                .setMaterial(new Material().setKs(1).setKd(0.6).setKr(0.3).setShininess(19));
        Polygon leftPolR = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, 120, -20), new Point(-500, 120, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon leftPolL = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 120, -20), new Point(-500, 120, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolR = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -120, -20), new Point(-500, -120, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolL = (Polygon) new Polygon(new Point(-500, -100, 0), new Point(lengthToX, -100, 0), new Point(lengthToX, -120, -20), new Point(-500, -120, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Color bowlingMain= new Color(WHITE).scale(10);
        BowlingPin bowlingPin1= new BowlingPin(new Ray(new Point(firstX,0,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin2= new BowlingPin(new Ray(new Point(firstX+distanceBetweenPins,-distanceBetweenPins/2,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin3= new BowlingPin(new Ray(new Point(firstX+distanceBetweenPins,distanceBetweenPins/2,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin4= new BowlingPin(new Ray(new Point(firstX+2*distanceBetweenPins,-distanceBetweenPins,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin5= new BowlingPin(new Ray(new Point(firstX+2*distanceBetweenPins,0,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin6= new BowlingPin(new Ray(new Point(firstX+2*distanceBetweenPins,distanceBetweenPins,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin7= new BowlingPin(new Ray(new Point(firstX+3*distanceBetweenPins,-1.5*distanceBetweenPins,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin8= new BowlingPin(new Ray(new Point(firstX+3*distanceBetweenPins,-distanceBetweenPins/2,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin9= new BowlingPin(new Ray(new Point(firstX+3*distanceBetweenPins,distanceBetweenPins/2,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        BowlingPin bowlingPin10= new BowlingPin(new Ray(new Point(firstX+3*distanceBetweenPins,1.5*distanceBetweenPins,0),new Vector(0,0,1)),bowlMat,bowlingMain,new Color(RED));
        _scene._geometries.add(bowlingPin1,lane,rightPolL,rightPolR,leftPolL,leftPolR,bowlingPin2,bowlingPin3,bowlingPin4,bowlingPin5,bowlingPin6,bowlingPin7,bowlingPin8,bowlingPin9,bowlingPin10);
        Polygon laneRight = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(laneColor)
                .setMaterial(new Material().setKs(1).setKd(0.6).setKr(0.3).setShininess(19));
        Polygon laneLeft = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(laneColor)
                .setMaterial(new Material().setKs(1).setKd(0.6).setKr(0.3).setShininess(19));
        _scene._geometries.add(laneLeft,laneRight);
        Polygon leftWall= (Polygon) new Polygon(new Point(-500, 340, 1500), new Point(lengthToX, 340, 1500), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(new Color(0, 122, 204))
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        Polygon rightWall= (Polygon) new Polygon(new Point(-500, -340, 1500), new Point(lengthToX, -340, 1500), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(new Color(0, 122, 204))
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        Polygon backWall= (Polygon) new Polygon(new Point(7000, 340, 1500), new Point(7000, -340, 1500), new Point(7000, -340, 0), new Point(7000, 340, 0)).setEmission(new Color(0, 122, 204))
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        _scene._geometries.add(leftWall,rightWall,backWall);

        Geometry leftBox= new Polygon(new Point(6000, 335, 100), new Point(lengthToX, 335, 100), new Point(lengthToX, 335, 0), new Point(6000, 335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        Geometry rightBox= new Polygon(new Point(6000, -335, 100), new Point(lengthToX, -335, 100), new Point(lengthToX, -335, 0), new Point(6000, -335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        Geometry secondBox= new Polygon(new Point(6000, -120, 100), new Point(lengthToX, -120, 100), new Point(lengthToX, -120, 0), new Point(6000, -120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        Geometry thirdBox= new Polygon(new Point(6000, 120, 100), new Point(lengthToX, 120, 100), new Point(lengthToX, 120, 0), new Point(6000, 120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        Geometry topBox= new Polygon(new Point(6000, -335, 100), new Point(lengthToX, -335, 100), new Point(lengthToX, 335, 100), new Point(6000, 335, 100)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        Geometry backBox= (Polygon) new Polygon(new Point(6995, 340, 100), new Point(6995, -340, 100), new Point(6995, -340, 0), new Point(6995, 340, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(1).setShininess(19));
        _scene._geometries.add(leftBox,rightBox,topBox,backBox,secondBox,thirdBox);







        Sphere lightCover= (Sphere)new Sphere(new Point(1500,100,100), 10d).setEmission(new Color(WHITE)) //
                .setMaterial(new Material().setKd(0.4).setKs(0.2).setShininess(19).setKt(0.2));
        _scene._geometries.add(lightCover);


        PointLight middleLight= new PointLight(new Color(GRAY),new Point(1500,100,100));

        _scene._lights.add(middleLight);





        camera.setImageWriter(new ImageWriter("ourPictureMP1", 500, 500)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();
        cameraTop.setImageWriter(new ImageWriter("ourPictureMP1Up", 500, 500)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }
}
