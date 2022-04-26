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
     * transparency coefficient
     */
    public Double3 _kt = Double3.ZERO;
    /**
     * reflectance coefficient
     */
    public Double3 _kr = Double3.ZERO;
    /**
     * level of shininess of material
     */
    public int _nshininess = 0;

    /**
     * setter for kD, builder pattern
     * @param kd diffuse factor
     * @return this
     */
    public Material setKd(double kd) {
        _kd = new Double3(kd);
        return this;
    }
    /**
     * setter for kR, builder pattern
     * @param kr reflectance coefficient
     * @return this
     */
    public Material setKr(double kr) {
        _kr = new Double3(kr);
        return this;
    }
  /**
     * setter for kt, builder pattern
     * @param kt transparency coefficient
     * @return this
     */
    public Material setKt(double kt) {
        _kt = new Double3(kt);
        return this;
    }

    /**
     * setter for kS, builder pattern
     *
     * @param ks specular factor
     * @return this
     */
    public Material setKs(double ks) {
        _ks = new Double3(ks);
        return this;
    }

    /**
     * setter for field nShininess, builder pattern
     *
     * @param nshininess level of shininess of material
     * @return this
     */
    public Material setShininess(int nshininess) {
        _nshininess = nshininess;
        return this;
    }
}
