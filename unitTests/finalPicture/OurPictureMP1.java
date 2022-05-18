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
        int lengthToX = 10000;
        int firstX = 5000;
        int distanceBetweenPins = 70;
        Color laneColor = new Color(255, 223, 128).reduce(9d / 5d);
        Material laneMaterial= new Material().setKs(0.7).setKd(0.1).setKr(0.05).setShininess(19);
        Material bowlPinMat = new Material().setKs(1).setShininess(19);

        Camera camera = new Camera(new Point(-1000, 0, 500), new Vector(1, 0, -0.05), new Vector(0.05, 0, 1)) //
                .setVPSize(200, 150).setVPDistance(Math.sqrt(500000));
        Camera cameraTop = new Camera(new Point(5000, 0, 5000), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
                .setVPSize(200, 150).setVPDistance(5000);


        Polygon lane = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, -100, 0), new Point(-500, -100, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        Polygon laneRight = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        Polygon laneLeft = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        _scene._geometries.add(lane, laneLeft, laneRight);
        Polygon leftPolR = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, 110, -20), new Point(-500, 110, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon leftPolM = (Polygon) new Polygon(new Point(-500, 110, -20), new Point(lengthToX, 110, -20), new Point(lengthToX, 130, -20), new Point(-500, 130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        _scene._geometries.add(leftPolM);
        Polygon leftPolL = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 130, -20), new Point(-500, 130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));


        Polygon rightPolR = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -130, -20), new Point(-500, -130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolM = (Polygon) new Polygon(new Point(-500, -110, -20), new Point(lengthToX, -110, -20), new Point(lengthToX, -130, -20), new Point(-500, -130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        _scene._geometries.add(rightPolM);
        Polygon rightPolL = (Polygon) new Polygon(new Point(-500, -100, 0), new Point(lengthToX, -100, 0), new Point(lengthToX, -110, -20), new Point(-500, -110, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Color bowlingMain = new Color(WHITE).scale(2);
        BowlingPin bowlingPin1 = new BowlingPin(new Ray(new Point(firstX, 0, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin2 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, -distanceBetweenPins / 2, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin3 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, distanceBetweenPins / 2, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin4 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, -distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin5 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, 0, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin6 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin7 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, -1.5 * distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin8 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, -distanceBetweenPins / 2, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin9 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, distanceBetweenPins / 2, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin10 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, 1.5 * distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        //_scene._geometries.add(bowlingPin1,lane,rightPolL,rightPolR,leftPolL,leftPolR,bowlingPin2,bowlingPin3,bowlingPin4,bowlingPin5,bowlingPin6,bowlingPin7,bowlingPin8,bowlingPin9,bowlingPin10);
        _scene._geometries.add(bowlingPin1, rightPolL, rightPolR, leftPolL, leftPolR, bowlingPin2, bowlingPin3, bowlingPin4, bowlingPin5, bowlingPin6);

        Color wallColor=new Color(0, 122, 204).reduce(1.8);
        Polygon leftWall = (Polygon) new Polygon(new Point(-500, 340, 1500), new Point(lengthToX, 340, 1500), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(wallColor)
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        Polygon rightWall = (Polygon) new Polygon(new Point(-500, -340, 1500), new Point(lengthToX, -340, 1500), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(wallColor)
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        Polygon backWall = (Polygon) new Polygon(new Point(7000, 340, 1500), new Point(7000, -340, 1500), new Point(7000, -340, 0), new Point(7000, 340, 0)).setEmission(wallColor)
                .setMaterial(new Material().setKd(0.9).setShininess(19));
        _scene._geometries.add(leftWall, rightWall, backWall);

        Geometry leftBox = new Polygon(new Point(6000, 335, 100), new Point(lengthToX, 335, 100), new Point(lengthToX, 335, 0), new Point(6000, 335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry rightBox = new Polygon(new Point(6000, -335, 100), new Point(lengthToX, -335, 100), new Point(lengthToX, -335, 0), new Point(6000, -335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry secondBox = new Polygon(new Point(6000, -120, 100), new Point(lengthToX, -120, 100), new Point(lengthToX, -120, 0), new Point(6000, -120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry thirdBox = new Polygon(new Point(6000, 120, 100), new Point(lengthToX, 120, 100), new Point(lengthToX, 120, 0), new Point(6000, 120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry topBox = new Polygon(new Point(6000, -335, 100), new Point(lengthToX, -335, 100), new Point(lengthToX, 335, 100), new Point(6000, 335, 100)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry backBox = (Polygon) new Polygon(new Point(6995, 340, 100), new Point(6995, -340, 100), new Point(6995, -340, 0), new Point(6995, 340, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        _scene._geometries.add(leftBox, rightBox, topBox, backBox, secondBox, thirdBox);

        int lightX = 3000;
        int lightZ = 550;
        Geometry lightFront = new Polygon(new Point(lightX - 25, -25, lightZ), new Point(lightX - 25, 25, lightZ), new Point(lightX - 15, 10, lightZ+20), new Point(lightX - 15, -10, lightZ+20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightRight = new Polygon(new Point(lightX - 25, -25, lightZ), new Point(lightX + 25, -25, lightZ), new Point(lightX + 15, -10, lightZ+20), new Point(lightX - 15, -10, lightZ+20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightBack = new Polygon(new Point(lightX + 25, -25, lightZ), new Point(lightX + 25, 25, lightZ), new Point(lightX + 15, 10, lightZ+20), new Point(lightX + 15, -10, lightZ+20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightLeft = new Polygon(new Point(lightX + 25, 25, lightZ), new Point(lightX - 25, 25, lightZ), new Point(lightX - 15, 10, lightZ+20), new Point(lightX + 15, 10, lightZ+20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Sphere lightCover = (Sphere) new Sphere(new Point(lightX, 0, lightZ), 15d).setEmission(new Color(ORANGE)) //
                .setMaterial(new Material().setKd(0.4).setKs(0.2).setShininess(19).setKt(0.3));
        _scene._geometries.add(lightCover, lightFront,lightRight,lightBack,lightLeft);

        PointLight middleLight = new PointLight(new Color(WHITE), new Point(lightX, 0, lightZ));

        _scene._lights.add(middleLight);


        camera.setImageWriter(new ImageWriter("ourPictureMP1", 2000, 2000)) //
                .setMaxLevelAdaptiveSS(5)
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImageAdaptiveSuperSampling() //
                .writeToImage();
        cameraTop.setImageWriter(new ImageWriter("ourPictureMP1Up", 500, 500)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

    }
}
