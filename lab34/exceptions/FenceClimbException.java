package lab34.exceptions;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class FenceClimbException extends Exception {
    private final String details;

    public FenceClimbException(String details) {
        super(details);
        this.details = details;
    }

    @Override public String getMessage() {
        return "Проблема с оградой: " + details + ".";
    }
}
