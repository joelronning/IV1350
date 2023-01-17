package se.kth.joeron.iv1350.pos.view;

import java.io.IOException;

import se.kth.joeron.iv1350.pos.controller.Controller;
import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;
import se.kth.joeron.iv1350.pos.util.SystemLogHandler;
import se.kth.joeron.iv1350.pos.util.TotalRevenueFileOutput;

/**
 * This class is a dummy replacement of a user interface for the POS program.
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errMsgHandler;
    private SystemLogHandler sysLogHandler;
    private TotalRevenueFileOutput fileOutputObserver;

    /**
     * Creates a new instance.
     * @param controller The controller that allows the <code>View</code> to communicate with the model.
     * 
     */
    public View(Controller controller) {
        this.controller = controller;
        this.controller.addObserver(new TotalRevenueView());
        errMsgHandler = new ErrorMessageHandler();

        try {
            fileOutputObserver = new TotalRevenueFileOutput();
            this.controller.addObserver(fileOutputObserver);
        }
        catch (IOException ioe) {
            errMsgHandler.displayErrorMessage("Failed to open file to save total revenue.");
        }

        try {
            sysLogHandler = new SystemLogHandler();
        }
        catch (IOException ioe) {
            errMsgHandler.displayErrorMessage("Failed to open log file. Please contact the " +
                                            "system administrator if this problem persists.");
        }
    }

    /**
     * Acts as a "dummy" view. Use this test the startNewSale scenario.
     */
    public void startNewSaleDummy() {
        if(this.controller.startNewSale()) {
            System.out.println("New sale started!");
        }
        else {
            System.out.println("Could not start new sale");
        }
        System.out.println();
    }

    /**
     * "Dummy" view. Use this to test the registerItem scenario.
     */
    public void registerItemDummy(int id) {
        try {
            SaleDTO saleInformation = controller.registerItem(id);
            System.out.println("Item added");
            System.out.println("Item list:");
            System.out.println(saleInformation.getItemList().toString());
            System.out.printf("Total: %.2f%n", (saleInformation.getTotalPrice()));
        }
        catch (ItemNotFoundException infe) {
            String errorMessage = "Could not find item with ID number: " + infe.getItemID() +
                                    "\nNo item registered, please check ID and try again!";
            errMsgHandler.displayErrorMessage(errorMessage);
        }
        catch (Exception exception) {
            String errorMessage = "There was an error when registering the last item, please try again!";
            writeToLogAndUI(errorMessage, exception);
        }
        System.out.println();
    }

    /**
     * "Dummy" view for endSale scenario.
     */
    public void endSaleDummy() {
        SaleDTO saleInformation = controller.endSale();
        System.out.println("Ending sale with the following items:");
        System.out.println(saleInformation.getItemList());
        System.out.printf("Total: %.2f%n", saleInformation.getTotalPrice());
        System.out.printf("VAT: %.2f%n", saleInformation.getVatAmount());
        System.out.printf("Amount to pay: %d%n", saleInformation.getAmountToPay());
        System.out.println();
    }

    /**
     * "Dummy" interaction to test registering payment and completing sale.
     * @param amountPaid The amount paid by the customer.
     */
    public void registerPaymentDummy(int amountPaid) {
        System.out.println("Registering payment...");
        int changeAmount = controller.registerPayment(amountPaid);

        System.out.println("\nDone!");
        System.out.println("Change: " + changeAmount);
        System.out.println();
    }

    /**
     * Closes the log file. This should be always be done before exiting program to prevent
     * file corruption.
     */
    public void cleanUp() {
        sysLogHandler.closeLogFile();
        fileOutputObserver.closeOutputFile();
    }

    private void writeToLogAndUI (String uiMessage, Exception exception) {
        errMsgHandler.displayErrorMessage(uiMessage);
        sysLogHandler.logException(exception);
    }
}
