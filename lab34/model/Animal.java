package lab34.model;

import lab34.domain.Location;
import lab34.domain.Mood;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public abstract class Animal extends Character {
    protected Animal(String name, Location location, Mood mood) {
        super(name, location, mood);
    }
}
