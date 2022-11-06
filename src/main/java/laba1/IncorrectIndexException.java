package laba1;

/**
 * The exception that is thrown when an invalid index is specified.
 * Used in {@link Container} class
 */
public class IncorrectIndexException extends Exception {

    /**
     * A constructor that involves a constructor from superclass {@link Exception}
     *
     * @param message Message to print
     */
    public IncorrectIndexException(String message) {
        super(message);
    }

}
