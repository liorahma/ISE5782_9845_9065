package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import primitives.*;

/**
 * Class representing scene
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class Scene {

    /**
     * name of scene
     */
    public String _name;
    /**
     * background color
     */
    public Color _background = Color.BLACK;
    /**
     * ambient light of scene
     */
    public AmbientLight _ambientLight = new AmbientLight();
    /**
     * the geometries present in the scene
     */
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
