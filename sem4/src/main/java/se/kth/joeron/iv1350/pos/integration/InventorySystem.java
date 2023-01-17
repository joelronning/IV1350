package se.kth.joeron.iv1350.pos.integration;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.exception.InventorySystemException;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;

/**
 * This class is responsible for communicating with the inventory system database.
 */
public class InventorySystem {
    ItemDTO[] dummyDatabase = {new ItemDTO(1, "Banana", "Origin: Colombia", 2.50, 1, 0.06),
                               new ItemDTO(2, "Milk 1 liter", "Milk from Swedish cows", 11.90, 1, 0.06),
                               new ItemDTO(3, "Cucumber", "Origin: Netherlands", 8.80, 1, 0.06),
                               new ItemDTO(4, "Hamburger 4p", "Frozen hamburgers 4x100g", 67.90, 1, 0.06),
                               new ItemDTO(5, "Toothbrush", "Toothbrush extra white 1p", 18.50, 1, 0.12)
                              };
    /**
     * Creates a new instance.
     */
    InventorySystem() {}

    /**
     * Retrieves information about a specified item from the inventory.
     * @throws ItemNotFoundException This exception is thrown when there is no matching ID number in
     * the database.
     * @throws InventorySystemException This exception is thrown whenever there is a problem with
     * the database program. In this iteration this is simulated by calling the method with ID number 66.
     * @param itemID Item identifer number.
     * @return <code>ItemDTO</code> containing information about the specified item.
     */
    public ItemDTO requestItemInfo(int itemID) throws ItemNotFoundException, InventorySystemException {
        if (itemID == 66)
            throw new InventorySystemException("Simulated database error");
        else if (itemID > this.dummyDatabase.length || itemID < 1)
            throw new ItemNotFoundException(itemID);

        return this.dummyDatabase[itemID - 1];
    }

    /**
     * Update inventory after sale.
     * @param saleInformation Information about which items have been purchased.
     */
    public void updateInventorySystem(SaleDTO saleInformation) {
        System.out.println("Inventory updated");
    }
}
