package se.kth.joeron.iv1350.pos.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for writing to the log file.
 */
public class SystemLogHandler {
    public SystemLogHandler () { }

    /**
     * Creates a log entry with information about thrown exception.
     * @param exception The exception to log.
     */
    public void logException (Exception exception) {
        StringBuilder logMessageSB = new StringBuilder();
        logMessageSB.append(getCurrentTime());
        logMessageSB.append(", Exception thrown: ");
        logMessageSB.append(exception.getMessage());

        System.out.println(logMessageSB);
        exception.printStackTrace(System.out);
    }
    
    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        return currentTime.format(timeFormat);
    }
}
