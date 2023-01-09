package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.dto.DiscountRulesDTO;

public class DBController {
    AccountingSystem accountingSystem;
    DiscountDatabase discountDB;
    InventorySystem inventorySystem;
   
    /**
     * Creates a new instance.
     */
    public DBController() {
        this.discountDB = new DiscountDatabase();
        this.accountingSystem = new AccountingSystem();
        this.inventorySystem = new InventorySystem();
    }

    /**
     * Update accounting system and inventory after sale.
     * @param saleInformation Information about which items have been purchased, price etc.
     */
    public void updateExternalSystems(SaleDTO saleInformation) {
        this.inventorySystem.updateInventorySystem(saleInformation);
        this.accountingSystem.updateAccountingSystem(saleInformation);
    }

    /**
     * Finds whether or not customer with given ID is eligible for a discount on the current sale.
     * @param customerID Unique customer number.
     * @param saleInformation Information about current sale.
     * @return <code>DiscountRulesDTO</code>
     */
    public DiscountRulesDTO discountRequest(int customerID, SaleDTO saleInformation) {
        return this.discountDB.discountRequest(customerID, saleInformation);
    }

    /**
     * Retrieves information about a specified item from the inventory.
     * @param itemID Item identifer number.
     * @return <code>ItemDTO</code>
     */
    public ItemDTO requestItemInfo(int itemID) {
        return this.inventorySystem.requestItemInfo(itemID);
    }
}
