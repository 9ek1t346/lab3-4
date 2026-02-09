package lab34.app;

import lab34.core.Scene;
import lab34.core.printer.ConsolePrinter;
import lab34.domain.Location;
import lab34.entities.*;
import lab34.exceptions.*;
import java.util.*;


/**
 * ЛР 3-4 (вариант 865765)
 */

public class Main {
    public static void main(String[] args) {
        long seed = (args.length > 0) ? parseLongOrDefault(args[0], System.nanoTime()) : System.nanoTime();
        Scene scene = new Scene(new ConsolePrinter(), new Random(seed), seed);

        scene.say("ЛР 3-4, вариант 865765. Seed=" + seed);
        scene.say("Однажды, гуляя по зоопарку, я заметил странное явление...");

        // Локации (record)
        Location zooAlley = new Location("Зоопарк: аллея у решётчатой ограды", 10, 5);
        Location enclosureInside = new Location("Вольер: за решёткой", 12, 5);
        Location outsideFence = new Location("Зоопарк: возле ограды", 10, 5);
        Location cityStreet = new Location("Город: улица", 100, 40);

        // Объекты
        Fence fence = new Fence("Решётчатая ограда", 190, true);
        Stick stick = new Stick("Небольшая палочка", 35);

        // Персонажи
        Narrator narrator = new Narrator("Я", zooAlley);

        Donkey donkey = new Donkey("Осёл", enclosureInside, true);
        ArrayList<Child> kids = new ArrayList<>();
        kids.add(Child.randomKid(scene.rnd(), outsideFence, true));
        kids.add(Child.randomKid(scene.rnd(), outsideFence, false));

        scene.say(narrator.describe());
        scene.say("Передо мной " + fence.describe() + ". За ней стоит " + donkey.describe() + ".");

        // 1) Превращение (checked exception)
        Shorty shorty;
        try {
            shorty = donkey.transform(scene);
        } catch (TransformationException ex) {
            scene.say("Мне стало не по себе: " + ex.getMessage());
            scene.say("Я моргнул — и всё равно что-то меняется (как будто мир \"догрузился\").");
            // Обработали checked исключение и продолжили сценарий.
            shorty = donkey.forceTransform(scene); // план Б, чтобы сценарий не развалился
        }

        // 2) Мальчики и палочка. Возможна ошибка сценария (unchecked exception)
        try {
            Child teaser = kids.get(scene.rnd().nextInt(kids.size()));
            scene.say("Возле ограды крутятся двое мальчиков: " + kids.get(0) + " и " + kids.get(1) + ".");
            scene.say(teaser.getName() + " решает подразнить " + shorty.getName() + ".");

            teaser.tease(shorty, stick, scene);

        } catch (ScenarioViolationException ex) {
            // Обработка unchecked исключения (требование 6)
            scene.say("Случилась глупость по сценарию: " + ex.getMessage());
            scene.say("Я делаю вид, что так и было задумано, и продолжаю наблюдение.");
        }

        // 3) Щелчок и испуг
        shorty.click(scene);

        // 4) Коротышка перелезает через ограду (checked exception)
        try {
            shorty.climbOver(fence, scene);
        } catch (FenceClimbException ex) {
            scene.say("Неожиданно: " + ex.getMessage());
            scene.say("Коротышка не сдаётся и ищет обход — через калитку для персонала.");
            shorty.goAroundFence(scene);
        }

        // 5) Погоня (вариативна)
        shorty.chase(kids, scene);

        // 6) Я пытаюсь проследить
        narrator.follow(shorty, scene);

        // 7) Спустя несколько дней — наблюдения в городе (ArrayList + вариативность)
        scene.say("\nПрошло несколько дней. Я иду по городу и замечаю знакомые фигуры...");
        ArrayList<Shorty> streetShorties = Shorty.generateStreetShorties(scene.rnd(), cityStreet);

        for (Shorty s : streetShorties) {
            scene.say("На улице: " + s.describe());
            s.doHooliganThing(scene);
        }

        narrator.conclude(streetShorties, scene);

        scene.say("\n(Конец прогона. Попробуй другой seed: java Main 1 / 2 / 999)");
    }

    private static long parseLongOrDefault(String s, long def) {
        try { return Long.parseLong(s.trim()); }
        catch (NumberFormatException e) { return def; }
    }
}
