package se.kth.joeron.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * This class is responsible for writing to the log file.
 */
public class SystemLogHandler {
    private static final String LOG_FILE_NAME = "pos-log.txt";
    private PrintWriter logFile;

    /**
     * Creates new instance of log handler and opens the file to log to.
     * @throws IOException Is thwron when the log file could not be opened.
     */
    public SystemLogHandler () throws IOException {
        logFile = new PrintWriter(new FileWriter(LOG_FILE_NAME, true));
    }

    /**
     * Creates a log entry with information about thrown exception.
     * @param exception The exception to log.
     */
    public void logException (Exception exception) {
        StringBuilder logMessageSB = new StringBuilder();
        logMessageSB.append(getCurrentTime());
        logMessageSB.append(", Exception thrown: ");
        logMessageSB.append(exception.getMessage());

        //System.out.println("---------------------Start of log entry--------------------");
        logFile.println(logMessageSB);
        exception.printStackTrace(logFile);
        //System.out.println("----------------------End of log entry---------------------");
    }

    /**
     * Closes the log file. This should be always be done before exiting program to avoid
     * file corruption.
     */
    public void closeLogFile() {
        logFile.close();
    }
    
    private String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter timeFormat = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

        return currentTime.format(timeFormat);
    }
}
