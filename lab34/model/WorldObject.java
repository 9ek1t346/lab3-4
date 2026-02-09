package lab34.model;

import java.util.Objects;
import java.util.UUID;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public abstract class WorldObject {
    private final UUID id = UUID.randomUUID();
    private final String name;

    protected WorldObject(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public final UUID getId() { return id; }
    public final String getName() { return name; }

    /** Абстрактный метод (требование: abstract class + abstract method) */
    public abstract String describe();
}
