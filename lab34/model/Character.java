package lab34.model;

import lab34.domain.Location;
import lab34.domain.Mood;
import java.util.Objects;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public abstract class Character extends WorldObject implements Movable {
    private Location location;
    private Mood mood;

    protected Character(String name, Location location, Mood mood) {
        super(name);
        this.location = Objects.requireNonNull(location);
        this.mood = Objects.requireNonNull(mood);
    }

    @Override public Location getLocation() { return location; }
    @Override public void moveTo(Location newLocation) { this.location = Objects.requireNonNull(newLocation); }

    public Mood getMood() { return mood; }
    public void setMood(Mood mood) { this.mood = Objects.requireNonNull(mood); }
}
