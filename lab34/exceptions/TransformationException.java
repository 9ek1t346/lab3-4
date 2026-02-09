package lab34.exceptions;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class TransformationException extends Exception {
    private final String details;

    public TransformationException(String details) {
        super(details);
        this.details = details;
    }

    @Override public String getMessage() {
        return "Ошибка превращения: " + details + ".";
    }
}
