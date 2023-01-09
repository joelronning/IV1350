package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;

public class SaleLog {
    /**
     * Creates a new instance.
     */
    SaleLog(){}

    /**
     * Stores sale information in the sale log external system.
     * @param saleInformation Information about current sale.
     */
    public void logSale(SaleDTO saleInformation) {
        System.out.println("Sale logged");
    }
}
