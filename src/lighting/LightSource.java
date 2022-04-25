package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * Interface representing light sources
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public interface LightSource {

    /**
     * gets intensity of the light source in a certain point
     * @param p point to heck
     * @return intensity
     */
    public Color getIntensity(Point p);

    /**
     * gets direction of light at a certain point
     * @param p point to check
     * @return direction vector
     */
    public Vector getL(Point p);

    /**
     * returns distance from light source to point
     * @param point point to calculate distance
     * @return distance
     */
    double getDistance(Point point);
}
