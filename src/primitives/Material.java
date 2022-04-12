package primitives;


/**
 * class representing a material
 *
 * @author Liorah Mandelbaum and Sarah Bednarsh
 */
public class Material {

    /**
     * specular factor
     */
    public Double3 _kd = Double3.ZERO;
    /**
     * diffuse factor
     */
    public Double3 _ks = Double3.ZERO;
    /**
     * level of shininess of material
     */
    public int _nshininess = 0;

    /**
     * setter for kD, builder pattern
     *
     * @param kd diffuse factor
     * @return this
     */
    public Material setKd(Double3 kd) {
        _kd = kd;
        return this;
    }

    /**
     * setter for kS, builder pattern
     *
     * @param ks specular factor
     * @return this
     */
    public Material setKs(Double3 ks) {
        _ks = ks;
        return this;
    }

    /**
     * setter for field nShininess, builder pattern
     *
     * @param nshininess level of shininess of material
     * @return this
     */
    public Material setNshininess(int nshininess) {
        _nshininess = nshininess;
        return this;
    }
}
