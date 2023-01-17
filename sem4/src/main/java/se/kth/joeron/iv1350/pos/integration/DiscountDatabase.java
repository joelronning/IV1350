package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.dto.DiscountRulesDTO;

/**
 * This class is responsible for communicating with the external discount
 * database (which is currently not implemented).
 */
public class DiscountDatabase {
    /**
     * Creates a new instance.
     */
    DiscountDatabase() {}
    
    /**
     * Finds whether or not customer with given ID is eligible for a discount on the current sale.
     * @param customerID Unique customer number.
     * @param saleInformation Information about current sale.
     * @return <code>DiscountRulesDTO</code>
     */
    public DiscountRulesDTO discountRequest(int customerID, SaleDTO saleInformation) {
        return new DiscountRulesDTO();
    }
}