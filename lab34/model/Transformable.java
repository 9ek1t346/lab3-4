package lab34.model;

import lab34.core.Scene;
import lab34.exceptions.TransformationException;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public interface Transformable<T> {
    T transform(Scene scene) throws TransformationException;
}
