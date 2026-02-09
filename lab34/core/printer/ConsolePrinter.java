package lab34.core.printer;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class ConsolePrinter implements TextPrinter {
    @Override public void println(String line) {
        System.out.println(line);
    }
}
