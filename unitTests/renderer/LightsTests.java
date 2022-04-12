package renderer;

import org.junit.jupiter.api.Test;

import lighting.*;
import geometries.*;
import primitives.*;
import scene.Scene;
import static java.awt.Color.*;

/**
 * Test rendering a basic image
 *
 * @author Dan
 */
public class LightsTests {
	private Scene _scene1 = new Scene("Test scene");
	private Scene _scene2 = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.15)));
	private Camera _camera1 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(150, 150) //
			.setVPDistance(1000);
	private Camera _camera2 = new Camera(new Point(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setVPSize(200, 200) //
			.setVPDistance(1000);

	private Point[] _p = { // The Triangles' vertices:
			new Point(-110, -110, -150), // the shared left-bottom
			new Point(95, 100, -150), // the shared right-top
			new Point(110, -110, -150), // the right-bottom
			new Point(-75, 78, 100) }; // the left-top
	private Point _trPL = new Point(30, 10, -100); // Triangles test Position of Light
	private Point _spPL = new Point(-50, -50, 25); // Sphere test Position of Light
	private Color _trCL = new Color(800, 500, 250); // Triangles test Color of Light
	private Color _spCL = new Color(800, 500, 0); // Sphere test Color of Light
	private Vector _trDL = new Vector(-2, -2, -2); // Triangles test Direction of Light
	private Material _material = new Material().setKd(0.5).setKs(0.5).setShininess(300);
	private Geometry _triangle1 = new Triangle(_p[0], _p[1], _p[2]).setMaterial(_material);
	private Geometry _triangle2 = new Triangle(_p[0], _p[1], _p[3]).setMaterial(_material);
	private Geometry _sphere = new Sphere(new Point(0, 0, -50), 50d) //
			.setEmission(new Color(BLUE).reduce(2)) //
			.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(300));

	/**
	 * Produce a picture of a sphere lighted by a directional light
	 */
	@Test
	public void sphereDirectional() {
		_scene1._geometries.add(_sphere);
		_scene1._lights.add(new DirectionalLight(_spCL, new Vector(1, 1, -0.5)));

		ImageWriter imageWriter = new ImageWriter("lightSphereDirectional", 500, 500);
		_camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a point light
	 */
	@Test
	public void spherePoint() {
		_scene1._geometries.add(_sphere);
		_scene1._lights.add(new PointLight(_spCL, _spPL).setKl(0.001).setKq(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightSpherePoint", 500, 500);
		_camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a spot light
	 */
	@Test
	public void sphereSpot() {
		_scene1._geometries.add(_sphere);
		_scene1._lights.add(new SpotLight(_spCL, _spPL, new Vector(1, 1, -0.5)).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpot", 500, 500);
		_camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a directional light
	 */
	@Test
	public void trianglesDirectional() {
		_scene2._geometries.add(_triangle1, _triangle2);
		_scene2._lights.add(new DirectionalLight(_trCL, _trDL));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesDirectional", 500, 500);
		_camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a point light
	 */
	@Test
	public void trianglesPoint() {
		_scene2._geometries.add(_triangle1, _triangle2);
		_scene2._lights.add(new PointLight(_trCL, _trPL).setKl(0.001).setKq(0.0002));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesPoint", 500, 500);
		_camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a spot light
	 */
	@Test
	public void trianglesSpot() {
		_scene2._geometries.add(_triangle1, _triangle2);
		_scene2._lights.add(new SpotLight(_trCL, _trPL, _trDL).setKl(0.001).setKq(0.0001));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpot", 500, 500);
		_camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a sphere lighted by a narrow spot light
	 */
	@Test
	public void sphereSpotSharp() {
		// for bonus
		_scene1._geometries.add(_sphere);
		//scene1._lights
		//		.add(new SpotLight(spCL, spPL, new Vector(1, 1, -0.5)).setNarrowBeam(10).setKl(0.001).setKq(0.00004));

		ImageWriter imageWriter = new ImageWriter("lightSphereSpotSharp", 500, 500);
		_camera1.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene1)) //
				.renderImage() //
				.writeToImage(); //
	}

	/**
	 * Produce a picture of a two triangles lighted by a narrow spot light
	 */
	@Test
	public void trianglesSpotSharp() {
		// for bonus
		_scene2._geometries.add(_triangle1, _triangle2);
		//scene2._lights.add(new SpotLight(trCL, trPL, trDL).setNarrowBeam(10).setKl(0.001).setKq(0.00004));

		ImageWriter imageWriter = new ImageWriter("lightTrianglesSpotSharp", 500, 500);
		_camera2.setImageWriter(imageWriter) //
				.setRayTracer(new RayTracerBasic(_scene2)) //
				.renderImage() //
				.writeToImage(); //
	}

}
