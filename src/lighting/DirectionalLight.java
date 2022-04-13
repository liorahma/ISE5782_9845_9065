package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class representing directional light
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class DirectionalLight extends Light implements LightSource {

    /**
     * direction of light
     */
    final private Vector _direction;

    /**
     * constructor for directional light
     *
     * @param intensity intensity for Light
     * @param direction direction of light
     */
    public DirectionalLight(Color intensity, Vector direction) {
        super(intensity);
        _direction = direction.normalize();
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity();
    }

    @Override
    public Vector getL(Point p) {
        return _direction;
    }
}
