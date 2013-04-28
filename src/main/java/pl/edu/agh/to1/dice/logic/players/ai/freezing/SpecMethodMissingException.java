package pl.edu.agh.to1.dice.logic.players.ai.freezing;

/**
 * Author: Piotr Turek
 */
public class SpecMethodMissingException extends RuntimeException {
    public SpecMethodMissingException() {
        super();
    }

    public SpecMethodMissingException(String message) {
        super(message);
    }

    public SpecMethodMissingException(String message, Throwable cause) {
        super(message, cause);
    }

    public SpecMethodMissingException(Throwable cause) {
        super(cause);
    }

    protected SpecMethodMissingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
