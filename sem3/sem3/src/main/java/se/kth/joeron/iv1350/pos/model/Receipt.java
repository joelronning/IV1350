package se.kth.joeron.iv1350.pos.model;

import java.time.LocalDateTime;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;

public class Receipt {
    private LocalDateTime dateAndTime;
    private String items;
    private double totalPrice;
    private double vatAmount;
    private int amountToPay;
    private int amountPaid;
    private int change;
    
    /**
     * Creates new instance.
     * @param saleInformation Contain information to be included on the receipt.
     */
    public Receipt(SaleDTO saleInformation) {
        this.dateAndTime = saleInformation.getDateAndTime();
        this.items = saleInformation.getItemList();
        this.totalPrice = saleInformation.getTotalPrice();
        this.vatAmount = saleInformation.getVatAmount();
        this.amountToPay = saleInformation.getAmountToPay();
        this.amountPaid = saleInformation.getAmountPaid();
        this.change = saleInformation.getChangeAmount();
    }

    /**
     * Returns date and time as a <code>java.time.LocalDateTime</code> object.
     * @return Object containing date and time.
     */
    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }

    /**
     * Returns a text representation of the list of items, including name, quantity and total price of each item.
     * @return <code>String</code> representation of item list.
     */
    public String getItems() {
        return this.items;
    }

    /**
     * Returns total price of sale, excluding VAT.
     * @return Total price of sale.
     */
    public String getPrice() {
        return String.format("%.2f", this.totalPrice);
    }

    /**
     * Returns total VAT amount for sale.
     * @return Sale VAT amount.
     */
    public String getVATAmount() {
        return String.format("%.2f", this.vatAmount);
    }

    /**
     * Returns the (rounded) amount to pay.
     * @return Rounded total price.
     */
    public int getAmountToPay() {
        return this.amountToPay;
    }

    /**
     * Returns the amount paid by customer.
     * @return Amount paid by the customer.
     */
    public int getAmountPaid() {
        return this.amountPaid;
    }

    /**
     * Returns amount of change given back to the customer.
     * @return Change amount.
     */
    public int getChangeAmount() {
        return this.change;
    }
}
