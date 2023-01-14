package se.kth.joeron.iv1350.pos.view;

import se.kth.joeron.iv1350.pos.controller.Controller;
import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;

public class View {
    Controller controller;
    ErrorMessageHandler errMsgHandler;

    /**
     * Creates a new instance.
     * @param controller The controller that allows the <code>View</code> to communicate with the model.
     * 
     */
    public View(Controller controller) {
        this.controller = controller;
        errMsgHandler = new ErrorMessageHandler();
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
            System.out.println();
        }
        catch (ItemNotFoundException exception) {
            String errorMessage = "No item with ID number: " + exception.getItemID();
            errMsgHandler.displayErrorMessage(errorMessage);
        }
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

    public void registerPaymentDummy(int amountPaid) {
        System.out.println("Registering payment...");
        int changeAmount = controller.registerPayment(amountPaid);

        System.out.println("\nDone!");
        System.out.println("Change: " + changeAmount);
        System.out.println();
    }
}
