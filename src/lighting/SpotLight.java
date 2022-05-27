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
    int _narrowBeam = 1;

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
    /**
     * returns intensity of light
     * @param p point to check
     * @return color of intensity
     */
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0d, _direction.dotProduct(super.getL(p))));
    }

    /**
     * sets marrow beam for light
     * @param narrowBeam degree of narrowness
     * @return the object- builder pattern
     */
    public SpotLight setNarrowBeam(int narrowBeam) {
        this._narrowBeam = narrowBeam;
        return this;
    }

    public int getNarrowBeam() {
        return _narrowBeam;
    }

}
