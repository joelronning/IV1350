package se.kth.joeron.iv1350.pos.exception;

/**
 * Thrown when failure related to the inventory database system happens.
 */
public class InventorySystemException extends Exception {
    public InventorySystemException (String message, Exception cause) {
        super(message, cause);
    }
}
