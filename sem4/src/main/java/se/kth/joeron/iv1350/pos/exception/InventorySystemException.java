package se.kth.joeron.iv1350.pos.exception;

/**
 * Thrown when failure related to the inventory database system happens.
 */
public class InventorySystemException extends Exception {
    /**
     * Creates new instance.
     * @param message An explaining error message.
     */
    public InventorySystemException (String message) {
        super(message);
    }
    
    /**
     * Creates new instance.
     * @param message An explaining error message.
     * @param cause The exception causing this exception.
     */
    public InventorySystemException (String message, Exception cause) {
        super(message, cause);
    }
}
