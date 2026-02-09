package lab34.entities;

import lab34.core.Scene;
import lab34.domain.*;
import lab34.exceptions.TransformationException;
import lab34.model.Animal;
import lab34.model.Transformable;

import java.util.Objects;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Donkey extends Animal implements Transformable<Shorty> {
    private final boolean behindFence;

    public Donkey(String name, Location location, boolean behindFence) {
        super(name, location, Mood.CALM);
        this.behindFence = behindFence;
    }

    public boolean isBehindFence() { return behindFence; }

    @Override
    public Shorty transform(Scene scene) throws TransformationException {
        if (!behindFence) {
            throw new TransformationException("осёл почему-то не за решёткой, и превращение выглядит неправдоподобно");
        }

        if (scene.chance(0.25)) {
            throw new TransformationException("превращение сорвалось: будто кто-то выключил магию на полсекунды");
        }

        return forceTransform(scene);
    }

    public Shorty forceTransform(Scene scene) {
        Outfit outfit = Outfit.donkeyStyle(scene.rnd());
        Shorty s = new Shorty("Коротышка", getLocation(), outfit, "ОСЁЛ");
        s.setMood(Mood.CURIOUS);

        scene.say("На моих глазах " + getName() + " вдруг превращается в " + s.getName() + "!");
        scene.say("Теперь он в одежде: " + outfit + ".");
        return s;
    }

    @Override public String describe() {
        return getName() + " (" + getMood() + "), место: " + getLocation().place();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Donkey other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Donkey{name='" + getName() + "', behindFence=" + behindFence + "}";
    }
}
