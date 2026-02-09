package lab34.exceptions;

/**
 * ЛР 3-4 (вариант 865765)
 * Сгруппировано по пакетам для удобства чтения в IntelliJ IDEA.
 */

public final class ScenarioViolationException extends RuntimeException {
    private final String details;

    public ScenarioViolationException(String details) {
        super(details);
        this.details = details;
    }

    @Override public String getMessage() {
        return "Нарушение логики сценария: " + details + ".";
    }
}
