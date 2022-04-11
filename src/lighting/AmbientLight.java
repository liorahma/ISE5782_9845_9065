package lighting;

import primitives.*;




/**
 * Class representing ambient light
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class AmbientLight {

    /**
     * intensity of ambient light (brightness - color)
     */
    private Color _intensity;
    /**
     * absorption coefficient
     */
    private Double3 _ka;

    public AmbientLight() {
        _intensity = Color.BLACK;
    }

    public AmbientLight(Color intensity, Double3 ka) {
        _intensity = intensity.scale(ka);
        _ka = ka;
    }

    public Color getIntensity() {
        return _intensity;
    }


}
