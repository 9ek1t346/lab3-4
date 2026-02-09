package lab34.model;

import lab34.domain.Location;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public interface Movable {
    Location getLocation();
    void moveTo(Location newLocation);
}
