package se.kth.joeron.iv1350.pos.exception;

/**
 * A generic exception class thrown when an action could not complete.
 * To be used in higher level classes that do not need specific information
 * about the cause of a runtime error.
 */
public class OperationFailedException extends Exception {
    /**
     * Creates a new instance of the exception.
     * @param message A message explaining the error.
     */
    public OperationFailedException (String message) {
        super(message);
    }
    
    /**
     * Creates a new instance of the exception.
     * @param message A message explaining the error.
     * @param cause The exception causing this exception.
     */
    public OperationFailedException (String message, Exception cause) {
        super(message, cause);
    }
}
