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
import scene.Scene.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OurPictureMP1 {
    private Scene _scene = new BuilderScene("Test scene").build();
    int lengthToX = 10000;
    int firstX = 5000;
    int distanceBetweenPins = 70;

    @Test
    public void picture() {

        Camera camera = new Camera(new Point(-1000, 0, 500), new Vector(1, 0, -0.05), new Vector(0.05, 0, 1)) //
                .setVPSize(200, 150).setVPDistance(Math.sqrt(500000));
        Camera cameraTop = new Camera(new Point(5000, 0, 580), new Vector(0, 0, -1), new Vector(1, 0, 0)) //
                .setVPSize(200, 150).setVPDistance(100);


        createLanes();

        createBowlingPins(firstX, 0);
        createBowlingPins(firstX, -240);
        createFallingBowlingPins(firstX, 240);

        createWalls();

        createBoxForEndOfLane();

        //SpotLight leftBottomSpotLight = new SpotLight(new Color(217, 102, 255).reduce(20), new Point(0, 200, 50), new Vector(1, -1, -0.5));
        //_scene._lights.add(leftBottomSpotLight);


        SpotLight trySomething2 = new SpotLight(new Color(102, 255, 51), new Point(4800, 240, 30), new Vector(1, 0, 0.2));
        _scene._lights.add(trySomething2);

        createBallHolder(2500, -150, 3);


        createLightFixture(new Point(3500, 0, 550));

        createTexture(new Ray(new Point(6999, -330, 200), new Vector(0, 1, 1)));

        //_scene._geometries.setBvhIsOn(false);
        camera.setImageWriter(new ImageWriter("ourPictureMP2BVH", 2000, 2000)) //
                .setRayTracer(new RayTracerBasic(_scene)) //
                .renderImage() //
                .writeToImage();

        cameraTop.setImageWriter(new ImageWriter("ourPictureMP2UpBVH", 500, 500)) //
               .setRayTracer(new RayTracerBasic(_scene)) //
             .renderImage() //
           .writeToImage();

    }

    private void createBallHolder(int ballHolderX, int ballHolderY, int ballHolderZ) {

        Material ballHolderMat = new Material().setShininess(19).setKt(0.95).setKd(0.2).setKr(0.1).setKs(1);
        Color ballHolderColor = new Color(140, 140, 140);
        Geometry bottomBallHolder = new Polygon(new Point(ballHolderX, ballHolderY, ballHolderZ), new Point(ballHolderX + 180, ballHolderY, ballHolderZ), new Point(ballHolderX + 180, ballHolderY - 100, ballHolderZ), new Point(ballHolderX, ballHolderY - 100, ballHolderZ))
                .setMaterial(ballHolderMat).setEmission(ballHolderColor);
        Geometry topBallHolder = new Polygon(new Point(ballHolderX, ballHolderY, ballHolderZ + 60), new Point(ballHolderX + 180, ballHolderY, ballHolderZ + 60), new Point(ballHolderX + 180, ballHolderY - 100, ballHolderZ + 60), new Point(ballHolderX, ballHolderY - 100, ballHolderZ + 60))
                .setMaterial(ballHolderMat).setEmission(ballHolderColor);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++) {
                _scene._geometries.add(new Sphere(new Point(ballHolderX + 25 + 50 * i, ballHolderY - 25 - 50 * j, ballHolderZ + 80), 20)
                        .setEmission(randomColor()).setMaterial(new Material().setShininess(25).setKs(1).setKd(0.45).setKr(0.35)));
                _scene._geometries.add(new Sphere(new Point(ballHolderX + 25 + 50 * i, ballHolderY - 25 - 50 * j, ballHolderZ + 20), 20)
                        .setEmission(randomColor()).setMaterial(new Material().setShininess(25).setKs(1).setKd(0.45).setKr(0.35)));
            }
        }
        _scene._geometries.add(bottomBallHolder, topBallHolder);
    }

    private void createLanes() {
        Material laneMaterial = new Material().setKs(0.7).setKd(0.1).setKr(0.05).setShininess(19);
        Color laneColor = new Color(255, 223, 128).reduce(9d / 5d);

        Polygon lane = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, -100, 0), new Point(-500, -100, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        Polygon laneRight = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        Polygon laneLeft = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(laneColor)
                .setMaterial(laneMaterial);
        Polygon leftPolR = (Polygon) new Polygon(new Point(-500, 100, 0), new Point(lengthToX, 100, 0), new Point(lengthToX, 110, -20), new Point(-500, 110, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon leftPolM = (Polygon) new Polygon(new Point(-500, 110, -20), new Point(lengthToX, 110, -20), new Point(lengthToX, 130, -20), new Point(-500, 130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Tube leftTube = (Tube) new Tube(new Ray(new Point(-500, 120, -15), new Vector(1, 0, 0)), 5)
                .setMaterial(new Material().setKs(1).setKd(0.6).setKr(0.4));
        Polygon leftPolL = (Polygon) new Polygon(new Point(-500, 140, 0), new Point(lengthToX, 140, 0), new Point(lengthToX, 130, -20), new Point(-500, 130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolR = (Polygon) new Polygon(new Point(-500, -140, 0), new Point(lengthToX, -140, 0), new Point(lengthToX, -130, -20), new Point(-500, -130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Polygon rightPolM = (Polygon) new Polygon(new Point(-500, -110, -20), new Point(lengthToX, -110, -20), new Point(lengthToX, -130, -20), new Point(-500, -130, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        Tube rightTube = (Tube) new Tube(new Ray(new Point(-500, -120, -15), new Vector(1, 0, 0)), 5)
                .setMaterial(new Material().setKs(1).setKd(0.6).setKr(0.4));
        Polygon rightPolL = (Polygon) new Polygon(new Point(-500, -100, 0), new Point(lengthToX, -100, 0), new Point(lengthToX, -110, -20), new Point(-500, -110, -20)).setEmission(new Color(GRAY))
                .setMaterial(new Material().setKs(1).setKd(0.3).setKr(0.3).setShininess(19));
        _scene._geometries.add(lane, laneLeft, laneRight);
        _scene._geometries.add(rightPolL, rightPolR, leftPolL, leftPolR, rightPolM, leftPolM);
        _scene._geometries.add(rightTube,leftTube);

    }

    private void createBowlingPins(int x, int y) {
        Material bowlPinMat = new Material().setKr(0.3).setKs(1).setShininess(19);
        Color bowlingMain = new Color(242, 242, 242).reduce(1.3);
        BowlingPin bowlingPin1 = new BowlingPin(new Ray(new Point(firstX, y, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin2 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, y - distanceBetweenPins / 2d, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin3 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, y + distanceBetweenPins / 2d, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin4 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y - distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin5 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin6 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y + distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin7 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, y - 1.5 * distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin8 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, y - distanceBetweenPins / 2d, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin9 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, y + distanceBetweenPins / 2d, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin10 = new BowlingPin(new Ray(new Point(firstX + 3 * distanceBetweenPins, y + 1.5 * distanceBetweenPins, 0), new Vector(0, 0, 1)), bowlPinMat, bowlingMain, new Color(RED));
        //_scene._geometries.add(bowlingPin1,bowlingPin2,bowlingPin3,bowlingPin4,bowlingPin5,bowlingPin6,bowlingPin7,bowlingPin8,bowlingPin9,bowlingPin10);
        _scene._geometries.add(bowlingPin1, bowlingPin2, bowlingPin3, bowlingPin4, bowlingPin5, bowlingPin6);

    }

    private void createFallingBowlingPins(int x, int y) {
        int height = 16;
        double zVector= 1;
        Material bowlPinMat = new Material().setKr(0.3).setKs(1).setShininess(19);
        Color bowlingMain = new Color(242, 242, 242).reduce(1.3);
        BowlingPin bowlingPin1 = new BowlingPin(new Ray(new Point(firstX, y, height), new Vector(1, 0, zVector)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin2 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, y - distanceBetweenPins / 2d, height), new Vector(1, -0.2,zVector)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin3 = new BowlingPin(new Ray(new Point(firstX + distanceBetweenPins, y + distanceBetweenPins / 2d, height), new Vector(1, 0.2, zVector)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin4 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y - distanceBetweenPins, height), new Vector(1, -0.2, zVector)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin5 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y, height), new Vector(1, 0, zVector)), bowlPinMat, bowlingMain, new Color(RED));
        BowlingPin bowlingPin6 = new BowlingPin(new Ray(new Point(firstX + 2 * distanceBetweenPins, y + distanceBetweenPins, 0), new Vector(1, 0.3, zVector)), bowlPinMat, bowlingMain, new Color(RED));
        Geometry ball= new Sphere(new Point(firstX,y-25,25),25)
                .setEmission(new Color(179, 0, 134)).setMaterial(new Material().setShininess(25).setKs(1).setKd(0.25).setKr(0.1));
        _scene._geometries.add(bowlingPin1, bowlingPin2, bowlingPin3, bowlingPin4, bowlingPin5, bowlingPin6,ball);

    }

    private void createWalls() {
        Material wallMat = new Material().setKd(0.6).setKr(0.2).setShininess(19);
        //Material wallMat= new Material().setKd(0.9).setShininess(19);
        Color wallColor = new Color(0, 122, 204).scale(1.8);
        //Color wallColor = new Color(255, 204, 102).scale(1.3);
//        Polygon leftWall = (Polygon) new Polygon(new Point(-500, 340, 1500), new Point(lengthToX, 340, 1500), new Point(lengthToX, 340, 0), new Point(-500, 340, 0)).setEmission(wallColor)
//                .setMaterial(wallMat);
        //create wall with space for window
        int closeX = 4000;
        int farX = 5500;
        int bottomZ = 300;
        int topZ = 450;
        Point topFarPoint = new Point(farX, 340, topZ);
        Point bottomFarPoint = new Point(farX, 340, bottomZ);
        Point topClosePoint = new Point(closeX, 340, topZ);
        Point bottomClosePoint = new Point(closeX, 340, bottomZ);
        Polygon leftWall1 = (Polygon) new Polygon(new Point(-500, 340, 1500), new Point(closeX, 340, 1500), new Point(closeX, 340, 0), new Point(-500, 340, 0)).setEmission(wallColor)
                .setMaterial(wallMat);
        Polygon leftWall2 = (Polygon) new Polygon(new Point(farX, 340, 1500), new Point(lengthToX, 340, 1500), new Point(lengthToX, 340, 0), new Point(farX, 340, 0)).setEmission(wallColor)
                .setMaterial(wallMat);
        Polygon leftWall3 = (Polygon) new Polygon(new Point(closeX, 340, 1500), new Point(farX, 340, 1500), new Point(farX, 340, topZ), new Point(closeX, 340, topZ)).setEmission(wallColor)
                .setMaterial(wallMat);
        Polygon leftWall4 = (Polygon) new Polygon(new Point(closeX, 340, bottomZ), new Point(farX, 340, bottomZ), new Point(farX, 340, 0), new Point(closeX, 340, 0)).setEmission(wallColor)
                .setMaterial(wallMat);
        Geometry verticalStick = new Polygon(new Point((farX + closeX) / 2d - 20, 339, topZ), new Point((farX + closeX) / 2d + 20, 339, topZ), new Point((farX + closeX) / 2d + 20, 339, bottomZ), new Point((farX + closeX) / 2d - 20, 339, bottomZ))
                .setEmission(new Color(LIGHT_GRAY));
        Geometry horizontalStick = new Polygon(new Point(closeX, 339, (topZ + bottomZ) / 2d - 5), new Point(farX, 339, (topZ + bottomZ) / 2d - 5), new Point(farX, 339, (topZ + bottomZ) / 2d + 5), new Point(closeX, 339, (topZ + bottomZ) / 2d + 5))
                .setEmission(new Color(LIGHT_GRAY));
        _scene._geometries.add(leftWall1, leftWall2, leftWall3, leftWall4, verticalStick, horizontalStick);
        //creating window
        Geometry window = new Polygon(topFarPoint, topClosePoint, bottomClosePoint, bottomFarPoint)
                .setMaterial(new Material().setKt(0.7).setKd(0.3).setShininess(1)).setEmission(new Color(230, 230, 255));
        _scene._geometries.add(window);
        _scene._lights.add(new DirectionalLight(new Color(255, 230, 128), new Vector(0, -1, -0.7)));

        Polygon rightWall = (Polygon) new Polygon(new Point(-500, -340, 1500), new Point(lengthToX, -340, 1500), new Point(lengthToX, -340, 0), new Point(-500, -340, 0)).setEmission(wallColor)
                .setMaterial(wallMat);
        Polygon backWall = (Polygon) new Polygon(new Point(7000, 340, 1500), new Point(7000, -340, 1500), new Point(7000, -340, 0), new Point(7000, 340, 0)).setEmission(wallColor)
                .setMaterial(wallMat);
        Geometry roof = new Polygon(new Point(7000, 340, 600), new Point(7000, -340, 600), new Point(-500, -340, 600), new Point(-600, 340, 600))
                .setEmission(new Color(0, 153, 204)).setMaterial(new Material().setKd(0.9).setShininess(19));
        _scene._geometries.add(rightWall, backWall, roof);
    }

    private void createBoxForEndOfLane() {
        int boxHeight = 200;
        Geometry leftBox = new Polygon(new Point(6000, 335, boxHeight), new Point(lengthToX, 335, boxHeight), new Point(lengthToX, 335, 0), new Point(6000, 335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry rightBox = new Polygon(new Point(6000, -335, boxHeight), new Point(lengthToX, -335, boxHeight), new Point(lengthToX, -335, 0), new Point(6000, -335, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry secondBox = new Polygon(new Point(6000, -120, boxHeight), new Point(lengthToX, -120, boxHeight), new Point(lengthToX, -120, 0), new Point(6000, -120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry thirdBox = new Polygon(new Point(6000, 120, boxHeight), new Point(lengthToX, 120, boxHeight), new Point(lengthToX, 120, 0), new Point(6000, 120, 0)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry topBox = new Polygon(new Point(6000, -335, boxHeight), new Point(lengthToX, -335, boxHeight), new Point(lengthToX, 335, boxHeight), new Point(6000, 335, boxHeight)).setEmission(new Color(BLACK))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        Geometry backBox = new Polygon(new Point(6995, 340, boxHeight), new Point(6995, -340, boxHeight), new Point(6995, -340, 0), new Point(6995, 340, 0)).setEmission(new Color(89, 89, 89))
                .setMaterial(new Material().setKs(0.8).setShininess(19));
        _scene._geometries.add(leftBox, rightBox, topBox, backBox, secondBox, thirdBox);
    }


    void createLightFixture(Point p) {
        double lightX = p.getX();
        double lightY = p.getY();
        double lightZ = p.getZ();
        Geometry lightFront = new Polygon(new Point(lightX - 25, lightY - 25, lightZ), new Point(lightX - 25, lightY + 25, lightZ), new Point(lightX - 15, lightY + 10, lightZ + 20), new Point(lightX - 15, lightY - 10, lightZ + 20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightRight = new Polygon(new Point(lightX - 25, lightY - 25, lightZ), new Point(lightX + 25, lightY - 25, lightZ), new Point(lightX + 15, lightY - 10, lightZ + 20), new Point(lightX - 15, lightY - 10, lightZ + 20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightBack = new Polygon(new Point(lightX + 25, lightY - 25, lightZ), new Point(lightX + 25, lightY + 25, lightZ), new Point(lightX + 15, lightY + 10, lightZ + 20), new Point(lightX + 15, lightY + -10, lightZ + 20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Geometry lightLeft = new Polygon(new Point(lightX + 25, lightY + 25, lightZ), new Point(lightX - 25, lightY + 25, lightZ), new Point(lightX - 15, lightY + 10, lightZ + 20), new Point(lightX + 15, lightY + 10, lightZ + 20))
                .setMaterial(new Material().setShininess(19).setKs(1)).setEmission(new Color(BLACK));
        Sphere lightCover = (Sphere) new Sphere(p, 15d).setEmission(new Color(ORANGE)) //
                .setMaterial(new Material().setKd(0.4).setKs(0.2).setShininess(19).setKt(0.3));
        _scene._geometries.add(lightCover, lightFront, lightRight, lightBack, lightLeft);

        SpotLight middleLight = new SpotLight(new Color(WHITE), new Point(lightX, 0, lightZ), new Vector(0, 0, -1));

        _scene._lights.add(middleLight);
    }

    void createTexture(Ray ray)//it goes from right to left
    {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 5; j++) {
                _scene._geometries.add(new Polygon(new Point(ray.getP0().getX(), ray.getP0().getY() + 15 + 30 * i, ray.getP0().getZ() + 30 * j), new Point(ray.getP0().getX(), ray.getP0().getY() + 30 * i, ray.getP0().getZ() + 15 + 30 * j), new Point(ray.getP0().getX(), ray.getP0().getY() + 15 + 30 * i, ray.getP0().getZ() + 30 + 30 * j), new Point(ray.getP0().getX(), ray.getP0().getY() + 30 + 30 * i, ray.getP0().getZ() + 15 + 30 * j))
                        //_scene._geometries.add(new Polygon(new Point(ray.getP0().getX(),ray.getP0().getY()+15+15*i,ray.getP0().getZ()+15*j),new Point(ray.getP0().getX(),ray.getP0().getY()+15*i,ray.getP0().getZ()+15+15*j),new Point(ray.getP0().getX(),ray.getP0().getY()+15+15*i,ray.getP0().getZ()+30 + 15*j),new Point(ray.getP0().getX(),ray.getP0().getY()+30 +15*i,ray.getP0().getZ()+15+15*j))
                        .setMaterial(new Material().setKs(1)).setEmission(randomColor()));

            }
        }
    }

    primitives.Color randomColor() {
        List<primitives.Color> col = new ArrayList<>();
        col.add(new Color(PINK));
        col.add(new Color(RED));
        col.add(new Color(GREEN));
        col.add(new Color(ORANGE));
        col.add(new Color(YELLOW));
        col.add(new Color(GRAY));
        col.add(new Color(255, 204, 221));
        col.add(new Color(153, 153, 255));
        col.add(new Color(210, 77, 255));
        col.add(new Color(255, 173, 153));
        col.add(new Color(255, 140, 102));
        //col.add(new Color(0, 204, 204));
        col.add(new Color(0, 204, 68));
        col.add(new Color(102, 153, 153));
        java.util.Random rand = new Random();
        return col.get(rand.nextInt(13));
    }


    public static class BowlingPin extends Intersectable {

        private final Geometries _spheres;

        public BowlingPin(Ray ray, Material material, Color all, Color special) {
            _spheres = new Geometries();
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(10)), 10).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(13)), 11).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(17)), 12).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(20)), 13).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(22)), 14).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(25)), 15).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(29)), 15.7).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(33)), 15).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(36)), 14).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(38)), 13).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(41)), 12).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(45)), 11).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(48)), 10).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(50)), 9).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(52)), 8).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(54)), 7).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(56)), 6).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(58)), 5).setMaterial(material).setEmission(special));//special
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(62)), 5).setMaterial(material).setEmission(special));//special
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(64)), 6).setMaterial(material).setEmission(special));//special
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(66)), 7).setMaterial(material).setEmission(all));
            _spheres.add(new Sphere(ray.getP0().add(ray.getDir().scale(68)), 8).setMaterial(material).setEmission(all));
        }


        @Override
        public void createBoundingBox() {
            _spheres.createBoundingBox();
            _box = _spheres._box;
        }

        @Override
        public List<Point> findIntersections(Ray ray) {
            return _spheres.findIntersections(ray);
        }

        @Override
        protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
            return _spheres.findGeoIntersections(ray, maxDistance);
        }


    }
}