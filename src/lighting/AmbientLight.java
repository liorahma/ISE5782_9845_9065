package lighting;

import primitives.*;

public class AmbientLight {

    private Color _intensity;
    private Double3 _ka;

    public AmbientLight() {
        _intensity = Color.BLACK;
    }

    public AmbientLight(Color intensity, Double3 ka) {
        _intensity = intensity;
        _ka = ka;
    }

    public Color getIntensity() {
        return _intensity;
    }


}
