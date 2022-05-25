package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Class representing scene
 *
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

    /**
     * list of light sources in the scene
     */
    public List<LightSource> _lights = new LinkedList<>();


    /**
     * constructor for scene
     *
     * @param name name of scene
     */
    public Scene(String name) {
        _name = name;
        _geometries = new Geometries();
    }

    /**
     * constructor for builder pattern
     * @param built built scene object
     */
    public Scene(BuilderScene built){
        _name = built._name;
        _geometries = built._geometries;
        _lights = built._lights;
        _background = built._background;
        _ambientLight = built._ambientLight;
    }


    /**
     * setter for background color, builder pattern
     *
     * @param background background color
     * @return this
     */
    public Scene setBackground(Color background) {
        _background = background;
        return this;
    }

    /**
     * setter for ambientLight, builder pattern
     *
     * @param ambientLight AmbientLight object
     * @return this
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        _ambientLight = ambientLight;
        return this;
    }

    /**
     * setter for geometries, builder pattern
     *
     * @param geometries geometries
     * @return this
     */
    public Scene setGeometries(Geometries geometries) {
        _geometries = geometries;
        return this;
    }

    /**
     * setter for light sources, builder pattern
     *
     * @param lights List of light sources for field light
     * @return this
     */
    public Scene setLights(List<LightSource> lights) {
        _lights = lights;
        return this;
    }


    /**
     * builder class for scene
     */
    public static class BuilderScene {
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

        /**
         * list of light sources in the scene
         */
        public List<LightSource> _lights = new LinkedList<>();


        /**
         * constructor for scene
         *
         * @param name name of scene
         */
        public BuilderScene(String name) {
            _name = name;
            _geometries = new Geometries();
        }

        /**
         * build
         *
         * @return scene
         */
        public Scene build() {
            return new Scene(this);
        }


        /**
         * setter for background color, builder pattern
         *
         * @param background background color
         * @return this
         */
        public BuilderScene setBackground(Color background) {
            _background = background;
            return this;
        }

        /**
         * setter for ambientLight, builder pattern
         *
         * @param ambientLight AmbientLight object
         * @return this
         */
        public BuilderScene setAmbientLight(AmbientLight ambientLight) {
            _ambientLight = ambientLight;
            return this;
        }

        /**
         * setter for geometries, builder pattern
         *
         * @param geometries geometries
         * @return this
         */
        public BuilderScene setGeometries(Geometries geometries) {
            _geometries = geometries;
            return this;
        }

        /**
         * setter for light sources, builder pattern
         *
         * @param lights List of light sources for field light
         * @return this
         */
        public BuilderScene setLights(List<LightSource> lights) {
            _lights = lights;
            return this;
        }

    }
}
