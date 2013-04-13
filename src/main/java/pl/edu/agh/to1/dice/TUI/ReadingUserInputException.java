package pl.edu.agh.to1.dice.TUI;

/**
 * Exception is thrown when reading from input stream caused some problems
 * @author Michal Partyka
 */
public class ReadingUserInputException extends Exception {
    public ReadingUserInputException(String message) {
        super(message);
    }

    public ReadingUserInputException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReadingUserInputException(Throwable cause) {
        super(cause);
    }
}
