package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;

public class AccountingSystem {
    /**
     * Creates a new instance.
     */
    AccountingSystem() {}

    /**
     * Creates a new instance.
     * 
     * @param saleInformation Information about the current sale.
     */
    public void updateAccountingSystem(SaleDTO saleInformation) {
        System.out.println("Accounting system updated");
    }
}
