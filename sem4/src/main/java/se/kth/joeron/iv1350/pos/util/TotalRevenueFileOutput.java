package se.kth.joeron.iv1350.pos.util;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import se.kth.joeron.iv1350.pos.model.TotalRevenueObserver;

/**
 * This class is used to write total daily revenue to a file.
 */
public class TotalRevenueFileOutput implements TotalRevenueObserver {
    private static final String TOTAL_REVENUE_FILE_NAME = "total-revenue.txt";
    private PrintWriter outputFile;

    /**
     * Creates new instance and opens the file to write to.
     * @throws IOException This is thrown if the specified file name could not be opened.
     */
    public TotalRevenueFileOutput() throws IOException {
        outputFile = new PrintWriter(new FileWriter(TOTAL_REVENUE_FILE_NAME));
    }

    /**
     * Writes new total revenue to output file.
     * @param totalRevenue The updated total revenue amount.
     */
    public void updateTotalRevenue(int totalRevenue) {
        StringBuilder totalRevenueMessageSB = new StringBuilder();
        totalRevenueMessageSB.append("The total revenue for today is: ");
        totalRevenueMessageSB.append(totalRevenue);
        outputFile.println(totalRevenueMessageSB);
    }

    /**
     * Closes the output file. This should be always be done before exiting program to avoid
     * file corruption.
     */
    public void closeOutputFile() {
        outputFile.close();
    }
}
