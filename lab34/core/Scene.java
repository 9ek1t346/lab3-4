package lab34.core;

import lab34.core.printer.TextPrinter;
import java.util.Objects;
import java.util.Random;

/**
 * ЛР 3-4 (вариант 865765)
 */

public final class Scene {
    private final TextPrinter out;
    private final Random rnd;
    private final long seed;

    public Scene(TextPrinter out, Random rnd, long seed) {
        this.out = Objects.requireNonNull(out);
        this.rnd = Objects.requireNonNull(rnd);
        this.seed = seed;
    }

    public Random rnd() { return rnd; }
    public long seed() { return seed; }

    public void say(String s) { out.println(s); }

    public int roll(int min, int maxInclusive) {
        return min + rnd.nextInt(maxInclusive - min + 1);
    }

    public boolean chance(double p) {
        return rnd.nextDouble() < p;
    }
}
