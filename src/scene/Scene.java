package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.*;

public class Scene {

    public String _name;
    public Color _background = Color.BLACK;
    public AmbientLight _ambientLight = new AmbientLight();
    public Geometries _geometries;

    public Scene(String name)
    {
        _name = name;
        _geometries = new Geometries();
    }


    public Scene setBackground(Color background) {
        _background = background;
        return this;
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return this;
    }
}
