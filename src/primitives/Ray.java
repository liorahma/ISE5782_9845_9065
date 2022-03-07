package primitives;

import java.util.Objects;

public class Ray {
    final private Point _p0;
    final private Vector _dir;

    /**
     * constructs Ray with point and vector
     * @param p0 starting point
     * @param dir direction vector
     */
    public Ray(Point p0, Vector dir) {
        this._p0 = p0;
        this._dir = dir.normalize(); //vector must be normalized
    }

    public Point getP0() {
        return _p0;
    }

    public Vector getDir() {
        return _dir;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray) obj;
        return Objects.equals(_p0, other._p0) && Objects.equals(_dir, other._dir);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_p0, _dir);
    }

    @Override
    public String toString() {
        return "Ray{" +
                "p0=" + _p0 +
                ", dir=" + _dir +
                '}';
    }
}
