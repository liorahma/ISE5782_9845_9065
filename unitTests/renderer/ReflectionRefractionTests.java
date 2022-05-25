/**
 * 
 */
package renderer;

import org.junit.jupiter.api.Test;

import static java.awt.Color.*;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;

/**
 * Tests for reflection and transparency functionality, test for partial shadows
 * (with transparency)
 * 
 * @author dzilb
 */
public class ReflectionRefractionTests {
	private Scene _scene = new Scene("Test scene");

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheres() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(150, 150).setVPDistance(1000);

		_scene._geometries.add( //
				new Sphere(new Point(0, 0, -50), 50d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setKt(0.3)),
				new Sphere(new Point(0, 0, -50), 25d).setEmission(new Color(RED)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)));
		_scene._lights.add( //
				new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2)) //
						.setKl(0.0004).setKq(0.0000006));

		camera.setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500)) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void twoSpheresOnMirrors() {
		Camera camera = new Camera(new Point(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(2500, 2500).setVPDistance(10000); //

		_scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));

		_scene._geometries.add( //
				new Sphere(new Point(-950, -900, -1000), 400d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point(-950, -900, -1000), 200d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500), new Point(670, 670, 3000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(1)),
				new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
						new Point(-1500, -1500, -2000)) //
						.setEmission(new Color(20, 20, 20)) //
						.setMaterial(new Material().setKr(0.5)));

		_scene._lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setKl(0.00001).setKq(0.000005));

		ImageWriter imageWriter = new ImageWriter("reflectionTwoSpheresMirrored", 500, 500);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light with a partially
	 * transparent Sphere producing partial shadow
	 */
	@Test
	public void trianglesTransparentSphere() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(200, 200).setVPDistance(1000);

		_scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));

		_scene._geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));

		_scene._lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
				.setKl(4E-5).setKq(2E-7));

		ImageWriter imageWriter = new ImageWriter("refractionShadow", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();
	}

	@Test
	public void allEffects1() {
		Camera camera = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setVPSize(500, 500).setVPDistance(1000);

//		LightSource pointLight = new PointLight(new Color(230, 156, 228), new Point(100, 40, 200));
//		_scene._lights.add(pointLight);

		_scene._lights.add(new SpotLight(new Color(700, 400, 400), new Point(100, 50, 200), new Vector(-100, -50, -150)) //
				.setKl(4E-5).setKq(2E-7));

		_scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.15));

		Geometry polyMirror = new Polygon(new Point(-150, -100, 0),
				new Point(-184.660049030367446, -39.577189086898301, 69.320098060734892),
				new Point(-178.588419505802932, 57.753112706325403, 57.176839011605836),
				new Point(-137.856740950870915, 94.660603586447394, -24.286518098258195),
				new Point(-103.196691920503469, 34.237792673345709, -93.606616158993106),
				new Point(-109.268321445067969, -63.092509119877974, -81.463357109864063))
				.setEmission(new Color(230, 230, 230))
				.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5));

		Geometry tempSphere = new Sphere(new Point(-100, 0, 50), 30d)
				.setEmission(new Color(255, 0, 0));

		_scene._geometries.add(polyMirror, tempSphere);


		_scene._geometries.add( //
				new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));

		_scene._geometries.add( //
				new Sphere(new Point(-100, 0, 50), 40d).setEmission(new Color(0, 0, 100)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20).setKt(0.5)),
				new Sphere(new Point(-100, 0, 50), 20d).setEmission(new Color(100, 20, 20)) //
						.setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)));

//
//		_scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
//
//		_scene._geometries.add( //
//				new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150)) //
//						.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)), //
//				new Sphere(new Point(60, 50, -50), 30d).setEmission(new Color(BLUE)) //
//						.setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)),
//				new Triangle(new Point(-70, 70, -140), new Point(75, 75, -150),new Point(80,80,-150)).setMaterial(new Material().setKd(0.2).setKs(0.2).setShininess(30).setKt(0.6)));
//
//		_scene._lights.add(new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1)) //
//				.setKl(4E-5).setKq(2E-7));
//		//scene._lights.add(new PointLight(new Color(BLUE),new Point(10,10,10)));




		ImageWriter imageWriter = new ImageWriter("allEffects1", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();
	}


	@Test
	public void tryAll()
	{
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
		ImageWriter imageWriter = new ImageWriter("tryAllAdaptiveSS", 600, 600);
		camera.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImageAdaptiveSuperSampling() //
				.writeToImage();

		camera.move(-1000,0,0);
		ImageWriter imageWriter2 = new ImageWriter("tryAllMove", 600, 600);
		camera.setImageWriter(imageWriter2) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();

		camera.move(1500,0,-100);
		camera.rotate(new Vector(0, 1, 0),350);
		ImageWriter imageWriter3 = new ImageWriter("tryAllRotate", 600, 600);
		camera.setImageWriter(imageWriter3) //
				.setRayTracer(new RayTracerBasic(_scene)) //
				.renderImage() //
				.writeToImage();
	}


}
