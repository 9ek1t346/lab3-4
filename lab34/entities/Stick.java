package lab34.entities;

import lab34.model.WorldObject;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Stick extends WorldObject {
    private final int lengthCm;

    public Stick(String name, int lengthCm) {
        super(name);
        this.lengthCm = lengthCm;
    }

    public int getLengthCm() { return lengthCm; }

    @Override public String describe() {
        return getName() + " (" + lengthCm + "см)";
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Stick other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Stick{name='" + getName() + "', lengthCm=" + lengthCm + "}";
    }
}
