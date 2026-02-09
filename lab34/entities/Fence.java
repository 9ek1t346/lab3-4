package lab34.entities;

import lab34.model.WorldObject;
import java.util.Objects;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Fence extends WorldObject {
    private final int heightCm;
    private final boolean climbable;

    public Fence(String name, int heightCm, boolean climbable) {
        super(name);
        this.heightCm = heightCm;
        this.climbable = climbable;
    }

    public int getHeightCm() { return heightCm; }
    public boolean isClimbable() { return climbable; }

    @Override public String describe() {
        return getName() + " (высота " + heightCm + "см, перелезаема=" + climbable + ")";
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Fence other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Fence{name='" + getName() + "', heightCm=" + heightCm + "}";
    }
}
