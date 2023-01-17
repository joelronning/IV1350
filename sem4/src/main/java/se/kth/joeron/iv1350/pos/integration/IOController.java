package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.model.Receipt;

/**
 * This class is responsible for all classes communicating with external IO-systems.
 */
public class IOController {
    Printer printer;
    SaleLog saleLog;

    /**
     * Creates a new instance.
     */
    public IOController() {
        this.printer = new Printer();
        this.saleLog = new SaleLog();
    }
    
    /**
     * Sends a printout request to external printer.
     * @param currentReceipt Receipt object with information about current sale.
     */
    public void printReceipt(Receipt currentReceipt) {
        this.printer.printReceipt(currentReceipt);
    }

    /**
     * Stores sale information in the sale log external system.
     * @param saleInformation Information about current sale.
     */
    public void updateSaleLog(SaleDTO saleInformation) {
        this.saleLog.logSale(saleInformation);
    }
}
