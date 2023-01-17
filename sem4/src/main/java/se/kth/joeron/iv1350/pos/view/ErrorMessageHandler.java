package se.kth.joeron.iv1350.pos.view;

/**
 * This class is responsible for writing errors to the user interface, whenever an
 * exception is caught in the view.
 */
public class ErrorMessageHandler {
    /**
     * Creates new instance.
     */
    public ErrorMessageHandler() {}

    /**
     * Shows specified error message to the user interface.
     * @param message Error message to be shown.
     */
    void displayErrorMessage (String message) {
        StringBuilder errorMessageSB = new StringBuilder();
        errorMessageSB.append("ERROR: ");
        errorMessageSB.append(message);

        System.out.println(errorMessageSB);
    }
}
