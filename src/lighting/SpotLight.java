package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class representing spotlight
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class SpotLight extends PointLight {
    /**
     * direction of spotlight
     */
    final private Vector _direction;

    /**
     * constructor for SpotLight
     *
     * @param intensity intensity for Light
     * @param position  location point for PointLight
     * @param direction direction of SpotLight
     */
    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        _direction = direction.normalize();
    }

    public Color getIntensity(Point p) {
        return super.getIntensity().scale(Math.max(0d, _direction.dotProduct(super.getL(p))));
    }
}
