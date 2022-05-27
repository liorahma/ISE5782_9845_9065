package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

/**
 * class representing point light
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class PointLight extends Light implements LightSource {
    /**
     * location of light source
     */
    final private Point _position;

    /**
     * attenuation factors
     */
    private double _kc = 1;
    private double _kl = 0;
    private double _kq = 0;

    /**
     * constructor for PointLight
     *
     * @param intensity for Light
     * @param position  location of point light
     */
    public PointLight(Color intensity, Point position) {
        super(intensity);
        _position = position;
    }

    /**
     * setter for kC using builder pattern
     *
     * @param kc attenuation factor
     * @return this
     */
    public PointLight setKc(double kc) {
        _kc = kc;
        return this;
    }

    /**
     * setter for kL using builder pattern
     *
     * @param kl attenuation factor
     * @return this
     */
    public PointLight setKl(double kl) {
        _kl = kl;
        return this;
    }

    /**
     * setter for kQ using builder pattern
     *
     * @param kq attenuation factor
     * @return this
     */
    public PointLight setKq(double kq) {
        _kq = kq;
        return this;
    }

    /**
     * returns intensity of light
     * @param p point to check
     * @return color of intensity
     */
    @Override
    public Color getIntensity(Point p) {
        double d = p.distance(_position);
        double dSquared = p.distanceSquared(_position);
        return super.getIntensity().reduce(_kc + _kl * d + _kq * dSquared);
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(_position).normalize();
    }

    /**
     * returns distance from given point
     * @param point point to calculate distance
     * @return distance
     */
    @Override
    public double getDistance(Point point) {
        return _position.distance(point);
    }
}
