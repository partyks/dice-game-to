package pl.edu.agh.to1.dice.logic.dices;

/**
 * Exception thrown when reading freeze indexes...
 * @author Michal Partyka
 */
public class FreezeIndexesReadingException extends Exception {
    public FreezeIndexesReadingException(String message) {
        super(message);
    }

    public FreezeIndexesReadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FreezeIndexesReadingException(Throwable cause) {
        super(cause);
    }

    public FreezeIndexesReadingException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
