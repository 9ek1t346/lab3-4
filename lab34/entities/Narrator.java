package lab34.entities;

import lab34.core.Scene;
import lab34.domain.Mood;
import lab34.domain.Location;
import lab34.model.Human;
import java.util.List;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class Narrator extends Human {
    public Narrator(String name, Location location) {
        super(name, location, Mood.CURIOUS);
    }

    public void follow(Shorty shorty, Scene scene) {
        scene.say("Я бросаюсь следом, стараясь не упустить " + shorty.getName() + " из виду...");
        setMood(Mood.CURIOUS);

        if (scene.chance(0.6)) {
            scene.say("Но они юркнули за поворот, и я почти ничего не увидел.");
            setMood(Mood.ANNOYED);
        } else {
            scene.say("Мне удалось заметить одежду: " + shorty.getOutfit() + ".");
        }
    }

    public void conclude(List<Shorty> shorties, Scene scene) {
        scene.say("\nЯ обдумываю увиденное.");
        if (shorties.isEmpty()) {
            scene.say("Странно: сегодня я никого не встретил. Может, мне всё это и правда показалось.");
            return;
        }

        long donkeyLike = shorties.stream().filter(s -> "ОСЁЛ".equals(s.getOrigin())).count();
        if (donkeyLike == shorties.size()) {
            scene.say("Вывод: эти коротышки ведут себя совсем не \"по-коротышечьи\".");
            scene.say("Похоже, это не коротышки, а бывшие ослы. То есть ослы, превратившиеся в коротышек.");
        } else {
            scene.say("Вывод: среди них точно есть странные типы. И не все они похожи на обычных людей.");
        }
    }

    @Override public String describe() {
        return getName() + " (" + getMood() + "), место: " + getLocation().place();
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Narrator other)) return false;
        return getId().equals(other.getId());
    }

    @Override public int hashCode() { return getId().hashCode(); }

    @Override public String toString() {
        return "Narrator{name='" + getName() + "', mood=" + getMood() + "}";
    }
}
