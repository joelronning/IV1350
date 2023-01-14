package se.kth.joeron.iv1350.pos.controller;

import se.kth.joeron.iv1350.pos.integration.IOController;
import se.kth.joeron.iv1350.pos.integration.DBController;
import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;
import se.kth.joeron.iv1350.pos.model.CashRegister;
import se.kth.joeron.iv1350.pos.model.Receipt;
import se.kth.joeron.iv1350.pos.model.Sale;

public class Controller {
    DBController externalSystems;
    IOController ioController;
    CashRegister cashRegister;
    Sale currentSale;
    
    /**
     * Creates a new instance.
     * @param externalSystems Takes the controller responsible for communicating with external databases.
     * @param ioController Takes the controller responsible for communicating with external IO.
     */
    public Controller(DBController externalSystems, IOController ioController) {
        this.externalSystems = externalSystems;
        this.ioController = ioController;
        this.cashRegister = new CashRegister(0);
    }

    /**
     * Creates a new <code>Sale</code> instance.
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public boolean startNewSale() {
        try {
            this.currentSale = new Sale();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Gets details for given <code>itemID</code> and adds it to the sale. If an item
     * of the same type (same ID) exists in the sale, increase its quantity by 1.
     * @param itemID The ID-number of item to register.
     * @return DTO with current state of <code>Sale</code> instance.
     */
    public SaleDTO registerItem(int itemID) throws ItemNotFoundException {
        ItemDTO itemInformation = externalSystems.requestItemInfo(itemID);
        return this.currentSale.registerItem(itemInformation);
    }

    //public SaleDTO enterQuantity(int quantity) {
    //
    //}

    //public SaleDTO discountRequest(int customerID) {
   
    //}

    /**
     * Returns information about current sale. Useful for showing price etc.
     * @return DTO with information about current sale.
     */
    public SaleDTO endSale() {
        return currentSale.endSale();
    }

    /**
     * Registers payment to current sale. Creates a receipt and updates external
     * databases and logs.
     * @param amount Amount paid by the customer.
     * @return Change amount for the customer.
     */
    public int registerPayment(int amount) {
        SaleDTO saleInformation = currentSale.registerPayment(amount);
        Receipt currenReceipt = new Receipt(saleInformation);
        this.ioController.printReceipt(currenReceipt);
        this.ioController.updateSaleLog(saleInformation);
        this.externalSystems.updateExternalSystems(saleInformation);
        this.cashRegister.addPayment(saleInformation.getAmountToPay());

        return saleInformation.getChangeAmount();
    }
}
