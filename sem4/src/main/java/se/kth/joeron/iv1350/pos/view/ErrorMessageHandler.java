package se.kth.joeron.iv1350.pos.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for writing errors to the user interface, whenever an
 * exception is caught in the view.
 */
public class ErrorMessageHandler {
    public ErrorMessageHandler() {}

    /**
     * Shows specified error message to the user interface.
     * @param message Error message to be shown.
     */
    void displayErrorMessage (String message) {
        StringBuilder errorMessageSB = new StringBuilder();
        errorMessageSB.append(getCurrentTime());
        errorMessageSB.append(", ERROR: ");
        errorMessageSB.append(message);

        System.out.println(errorMessageSB);
    }
    
    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        return currentTime.format(timeFormat);
    }
}
