package se.kth.joeron.iv1350.pos.dto;

public class ItemDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private double vatRate;
   
    /**
     * Creates a new instance of <code>ItemDTO</code> which holds information about an item.
     * Overloading with no parameters used for testing purposes.
     */
    public ItemDTO() {}
    
    /**
     * Creates a new instance of <code>ItemDTO</code> which holds information about an item.
     * @param id Item ID number.
     * @param name Item name.
     * @param description Item description.
     * @param price Item price.
     * @param quantity Item quantity.
     * @param vatRate VAT rate for item.
     */
    public ItemDTO(int id, String name, String description, double price, int quantity, double vatRate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.vatRate = vatRate;
    }

    /**
     * Returns item ID number.
     * @return <code>int</code>
     */
    public int getID() {
        return this.id;
    }

    /**
     * Returns item name.
     * @return <code>String</code>
     */
    public String getName() {
        return this.name;
    }

    /**
     * Returns item description.
     * @return <code>String</code>
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns price of item (price per item).
     * @return <code>double</code>
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns quantity of item, can be quantity to purchase or quantity in stock.
     * @return <code>int</code>
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Returns VAT rate of item.
     * @return <code>double</double>
     */
    public double getVatRate() {
        return this.vatRate;
    }
}
