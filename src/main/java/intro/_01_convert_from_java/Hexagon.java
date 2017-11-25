package intro._01_convert_from_java;

import java.util.Objects;

/**
 * A plain old Java object with all the boilerplate.
 */
@SuppressWarnings("ALL")
public class Hexagon {

    private final int x;
    private final int y;
    private final int z;

    public Hexagon(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hexagon hexagon = (Hexagon) o;
        return getX() == hexagon.getX() &&
                getY() == hexagon.getY() &&
                getZ() == hexagon.getZ();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getZ());
    }

    @Override
    public String toString() {
        return "Hexagon{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}