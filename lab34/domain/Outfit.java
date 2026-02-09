package lab34.domain;

import java.util.Random;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public record Outfit(ClothingColor trousers, ClothingColor top, String hat) {
    public static Outfit donkeyStyle(Random rnd) {
        ClothingColor trousers = rnd.nextBoolean() ? ClothingColor.YELLOW : ClothingColor.GREENISH_YELLOW;
        ClothingColor top = rnd.nextBoolean() ? ClothingColor.STRIPED : ClothingColor.DARK;
        String hat = rnd.nextBoolean() ? "пёстрый берет с кисточкой" : "простая кепка";
        return new Outfit(trousers, top, hat);
    }
}
