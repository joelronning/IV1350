package se.kth.joeron.iv1350.pos.exception;

/**
 * Thrown when trying to retrieve information about an item ID number that
 * does not match any entry in the inventory database.
 */
public class ItemNotFoundException extends Exception {
    int itemID;
    
    /**
     * Creates an instance of the exception.
     * @param itemIDNumber The ID number that did not correspond to an item.
     */
    public ItemNotFoundException (int itemIDNumber) {
        super("Could not find item with number " + itemIDNumber);
        itemID = itemIDNumber;
    }

    /**
     * Return ID number that could not be found.
     * @return ID number (<code>int</code>)
     */
    public int getItemID () {
        return itemID;
    }
}