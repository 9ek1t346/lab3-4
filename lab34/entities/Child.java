package lab34.entities;

import lab34.core.Scene;
import lab34.domain.*;
import lab34.exceptions.ScenarioViolationException;
import lab34.model.Human;

import java.util.Objects;
import java.util.Random;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Child extends Human {
    private final Outfit outfit;
    private final int mischief;     // 1..10
    private final boolean hasStickIdea;

    private Child(String name, Location location, Outfit outfit, int mischief, boolean hasStickIdea) {
        super(name, location, Mood.CALM);
        this.outfit = Objects.requireNonNull(outfit);
        this.mischief = mischief;
        this.hasStickIdea = hasStickIdea;
    }

    public static Child randomKid(Random rnd, Location location, boolean moreYellow) {
        ClothingColor trousers = moreYellow ? ClothingColor.YELLOW : (rnd.nextBoolean() ? ClothingColor.COLORFUL : ClothingColor.LIGHT);
        ClothingColor top = rnd.nextBoolean() ? ClothingColor.COLORFUL : ClothingColor.STRIPED;
        String hat = rnd.nextBoolean() ? "кепка" : "без шапки";
        Outfit outfit = new Outfit(trousers, top, hat);

        int mischief = 3 + rnd.nextInt(8);  // 3..10
        boolean hasStickIdea = rnd.nextBoolean();
        String name = rnd.nextBoolean() ? "Мальчик" : "Паренёк";
        return new Child(name, location, outfit, mischief, hasStickIdea);
    }

    public String getOutfit() { return outfit.toString(); }

    public void tease(Shorty target, Stick stick, Scene scene) {
        Objects.requireNonNull(target);

        setMood(Mood.CURIOUS);
        scene.say(getName() + " в " + outfit + " корчит рожи и подскакивает поближе.");

        boolean willUseStick = hasStickIdea && scene.chance(mischief / 12.0);

        if (willUseStick) {
            if (stick == null) {
                throw new ScenarioViolationException("палочка должна быть, но её нет");
            }
            scene.say(getName() + " размахивает " + stick.describe() + " перед носом " + target.getName() + ".");
            target.setMood(Mood.ANNOYED);
        } else {
            scene.say(getName() + " дразнит словами и жестами (без палочки).");
            if (scene.chance(0.3)) target.setMood(Mood.ANNOYED);
        }

        if (scene.chance(0.15)) {
            setMood(Mood.AFRAID);
            scene.say(getName() + " внезапно сам пугается и отскакивает на шаг.");
        }
    }

    @Override public String describe() {
        return getName() + " (" + getMood() + "), одежда: " + outfit + ", шкодливость=" + mischief;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Child other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Child{name='" + getName() + "', outfit=" + outfit + ", mischief=" + mischief + ", mood=" + getMood() + "}";
    }
}
