package lighting;

import primitives.*;


/**
 * Class representing ambient light
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class AmbientLight extends Light {

    /**
     * default constructor for ambient light
     */
    public AmbientLight() {
        super(Color.BLACK);
    }

    /**
     * constructor for ambient light with Double3
     * @param intensity intensity of ambient light
     * @param ka attenuation coefficient
     */
    public AmbientLight(Color intensity, Double3 ka) {
        super(intensity.scale(ka));
    }
    /**
     * constructor for ambient light with double
     * @param intensity intensity of ambient light
     * @param ka attenuation coefficient
     */
    public AmbientLight(Color intensity, double ka) {
        super(intensity.scale(new Double3(ka,ka,ka)));
    }



}
