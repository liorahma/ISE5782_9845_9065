package lighting;

import primitives.Color;

/**
 * abstract class representing Light
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
abstract class Light {

    /**
     * light color
     */
    final private Color _intensity;

    /**
     * constructor for Light
     *
     * @param intensity intensity of light
     */
    public Light(Color intensity) {
        _intensity = intensity;
    }

    /**
     * getter for intensity
     *
     * @return intensity
     */
    public Color getIntensity() {
        return _intensity;
    }

}

