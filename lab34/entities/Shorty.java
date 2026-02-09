package lab34.entities;

import lab34.core.Scene;
import lab34.domain.*;
import lab34.exceptions.FenceClimbException;
import lab34.model.Human;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Shorty extends Human {
    private final Outfit outfit;
    private final String origin; // "ОСЁЛ" по сюжету

    public Shorty(String name, Location location, Outfit outfit, String origin) {
        super(name, location, Mood.CURIOUS);
        this.outfit = Objects.requireNonNull(outfit);
        this.origin = Objects.requireNonNull(origin);
    }

    public Outfit getOutfit() { return outfit; }
    public String getOrigin() { return origin; }

    public void click(Scene scene) {
        if (getMood() == Mood.ANNOYED) {
            scene.say(getName() + " сердито щёлкает так, что один из мальчиков отскакивает в сторону!");
            setMood(Mood.ANGRY);
        } else {
            scene.say(getName() + " щёлкает пальцами — не то предупреждение, не то шутка.");
            setMood(Mood.CURIOUS);
        }
    }

    public void climbOver(Fence fence, Scene scene) throws FenceClimbException {
        Objects.requireNonNull(fence);

        if (!fence.isClimbable() || fence.getHeightCm() > 220) {
            throw new FenceClimbException("ограда слишком сложная для перелезания прямо сейчас");
        }

        if (scene.chance(0.2)) {
            throw new FenceClimbException("сорвался на верхней перекладине и решил не рисковать");
        }

        scene.say(getName() + " ловко перелезает через ограду.");
        setMood(Mood.ANGRY);
    }

    public void goAroundFence(Scene scene) {
        scene.say(getName() + " обегает ограду сбоку и оказывается снаружи.");
        setMood(Mood.ANGRY);
    }

    public void chase(List<Child> kids, Scene scene) {
        scene.say(getName() + " бросается в погоню за мальчишками!");
        setMood(Mood.ANGRY);

        for (Child kid : kids) {
            boolean kidEscapes = scene.chance(0.65);
            if (kidEscapes) {
                scene.say(kid.getName() + " удирает зигзагами и ускользает за киоском.");
                kid.setMood(Mood.AFRAID);
            } else {
                scene.say(kid.getName() + " спотыкается, но в последний момент вырывается и тоже исчезает.");
                kid.setMood(Mood.ANNOYED);
            }
        }

        if (scene.chance(0.7)) {
            scene.say("Через минуту они растворяются в толпе, и погоня сходит на нет.");
            setMood(Mood.ANNOYED);
        } else {
            scene.say(getName() + " ещё немного бегает кругами, будто проверяя, кто здесь главный.");
            setMood(Mood.CURIOUS);
        }
    }

    public void doHooliganThing(Scene scene) {
        int act = scene.roll(1, 3);
        switch (act) {
            case 1 -> scene.say(getName() + " носится по тротуару и цепляет прохожих локтями.");
            case 2 -> scene.say(getName() + " строит гримасы и внезапно щёлкает пальцами.");
            case 3 -> scene.say(getName() + " спорит с другим коротышкой и размахивает руками.");
        }
    }

    public static ArrayList<Shorty> generateStreetShorties(Random rnd, Location cityStreet) {
        ArrayList<Shorty> list = new ArrayList<>();
        int count = 1 + rnd.nextInt(4); // 1..4

        for (int i = 0; i < count; i++) {
            Outfit outfit = Outfit.donkeyStyle(rnd);
            Shorty s = new Shorty("Коротышка#" + (i + 1), cityStreet, outfit, "ОСЁЛ");
            s.setMood(rnd.nextBoolean() ? Mood.CURIOUS : Mood.ANNOYED);
            list.add(s);
        }
        return list;
    }

    @Override public String describe() {
        return getName() + " (" + getMood() + "), одежда: " + outfit + ", происхождение: " + origin;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shorty other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Shorty{name='" + getName() + "', mood=" + getMood() + ", outfit=" + outfit + ", origin='" + origin + "'}";
    }
}
