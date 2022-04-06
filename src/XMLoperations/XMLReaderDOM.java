package XMLoperations;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import primitives.Color;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;
import scene.Scene;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


/**
 * class for analyzing data of xml file into scene, using DOM library
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class XMLReaderDOM {
    /**
     * Builds scene from a given xml file
     * @param sceneName name of scene
     * @param xmlPath path of xml file
     * @return scene object
     */
    public static Scene buildSceneFromXML(String sceneName, String xmlPath) {
        Scene scene = new Scene(sceneName);

        // Instantiate the Factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            // parse XML file
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(new File(xmlPath));


            // get scene from file
            Element sceneElement = (Element) doc.getElementsByTagName("scene").item(0);

            // get background
            Color background = getColorFromElement(sceneElement, "background-color");
            scene.setBackground(background);

            // get ambient light
            Element colorElement = (Element) doc.getElementsByTagName("ambient-light").item(0);
            Color ambient = getColorFromElement(colorElement, "color");
            scene.setAmbientLight(new AmbientLight(ambient, new Double3(1, 1, 1)));

            // get geometries
            Element geometriesElement = (Element) doc.getElementsByTagName("geometries").item(0);
            Geometries geometries = new Geometries();

            // extract spheres
            NodeList spheres = geometriesElement.getElementsByTagName("sphere");
            for (int i = 0; i < spheres.getLength(); i++) {
                Element sphere = (Element) spheres.item(i);
                Point center = getPointFromElement(sphere, "center");
                double radius = Double.parseDouble(sphere.getAttribute("radius"));
                geometries.add(new Sphere(center, radius));
            }

            // extract triangles
            NodeList triangles = geometriesElement.getElementsByTagName("triangle");
            for (int i = 0; i < triangles.getLength(); i++) {
                Element triangle = (Element) triangles.item(i);
                Point p0 = getPointFromElement(triangle, "p0");
                Point p1 = getPointFromElement(triangle, "p1");
                Point p2 = getPointFromElement(triangle, "p2");
                geometries.add(new Triangle(p0, p1, p2));
            }

            // extract planes
            NodeList planes = geometriesElement.getElementsByTagName("plane");
            for (int i = 0; i < planes.getLength(); i++) {
                Element plane = (Element) planes.item(i);
                Point q0 = getPointFromElement(plane, "q0");
                Vector normal = getVectorFromElement(plane, "normal");
                geometries.add(new Plane(q0, normal));
            }

            scene.setGeometries(geometries);
            return scene;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            return null;
        }

    }

    /**
     * Gets color from a certain element according to the given attribute
     * @param colorElement element
     * @param attribute attribute of color
     * @return color in rgb
     */
    private static Color getColorFromElement(Element colorElement, String attribute) {
        String[] rgbStr = colorElement.getAttribute(attribute).split(" ");
        return new Color(Integer.parseInt(rgbStr[0]),
                Integer.parseInt(rgbStr[1]),
                Integer.parseInt(rgbStr[2]));
    }

    /**
     * Gets point from a certain element according to the given attribute
     * @param pointElement element
     * @param attribute attribute of point
     * @return Point object
     */
    private static Point getPointFromElement(Element pointElement, String attribute) {
        String[] pointStr = pointElement.getAttribute(attribute).split(" ");
        return new Point(Integer.parseInt(pointStr[0]),
                Integer.parseInt(pointStr[1]),
                Integer.parseInt(pointStr[2]));
    }

    /***
     * Gets vector from a certain element according to the given attribute
     * @param vectorElement element
     * @param attribute attribute of vector
     * @return Vector object
     */
    private static Vector getVectorFromElement(Element vectorElement, String attribute) {
        String[] pointStr = vectorElement.getAttribute(attribute).split(" ");
        return new Vector(Integer.parseInt(pointStr[0]),
                Integer.parseInt(pointStr[1]),
                Integer.parseInt(pointStr[2]));
    }
}
