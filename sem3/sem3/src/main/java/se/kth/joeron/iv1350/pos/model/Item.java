package se.kth.joeron.iv1350.pos.model;

import se.kth.joeron.iv1350.pos.dto.ItemDTO;

public class Item {
    private String name;
    private int id;
    private String description;
    private double price;
    private double vatRate;
    private int quantity;

    /**
     * Creates new instance.
     * @param itemInformation Information about item properties.
     */
    Item(ItemDTO itemInformation) {
        this.name = itemInformation.getName();
        this.id = itemInformation.getID();
        this.description = itemInformation.getDescription();
        this.price = itemInformation.getPrice();
        this.vatRate = itemInformation.getVatRate();
        this.quantity = itemInformation.getQuantity();
    }
   
    /**
     * Increases item <code>quantity</code> by 1.
     */
    public void increaseQuantityBy1() {
        this.quantity++;
    }

    /**
     * Sets the quantity of item to a specified value.
     * @param quantity Value to set quantity to.
     */
    public void changeQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets item identifier.
     * @return Item identifier.
     */
    public int getID() {
        return this.id;
    }

    /**
     * Gets item name.
     * @return String containing item name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Calculates and returns the total amount for this item, incl VAT.
     * @return Price for <code>quantity</code> number of items.
     */
    public double getPrice() {
        return this.price * this.quantity; 
    }

    /**
     * Returns total VAT amount for item.
     * @return Item VAT amount.
     */
    public double getVATAmount() {
        return this.price * this.quantity * this.vatRate;
    }

    /**
     * Returns item quantity.
     * @return Quantity of the item.
     */
    public int getQuantity() {
        return this.quantity;
    }
}
