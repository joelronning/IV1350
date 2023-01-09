package se.kth.joeron.iv1350.pos.dto;

import java.time.LocalDateTime;

public class SaleDTO {
    private String itemList;
    private LocalDateTime dateAndTime;
    private double price;
    private double vatAmount;
    private double totalPrice;
    private int amountToPay;
    private int amountPaid;
    private int changeAmount;
    
    /**
     * Creates instance of <code>SaleDTO</code> which holds information about a sale.
     * @param itemList List of items purchased in the sale.
     * @param dateAndTime Date and time of sale.
     * @param price Total price of sale, excluding VAT.
     * @param vatAmount VAT amount of sale.
     * @param totalPrice Total price of sale, including.
     * @param amountPaid How much the customer paid.
     * @param changeAmount How much change the customer got/will get.
     */
    public SaleDTO(String itemList, LocalDateTime dateAndTime, double price, double vatAmount, double totalPrice, int amountToPay, int amountPaid, int changeAmount) {
        this.itemList = itemList;
        this.dateAndTime = dateAndTime;
        this.price = price;
        this.vatAmount = vatAmount;
        this.totalPrice = totalPrice;
        this.amountToPay = amountToPay;
        this.amountPaid = amountPaid;
        this.changeAmount = changeAmount;
    }

    /**
     * Returns the list of items purchased.
     * @return <code>String</code> of items.
     */
    public String getItemList() {
        return this.itemList;
    }

    /**
     * Returns date and time of sale.
     * @return Time and date of sale, <code>java.util.Date</code>
     */
    public LocalDateTime getDateAndTime() {
        return this.dateAndTime;
    }

    /**
     * Returns the price (excluding VAT) of the sale.
     * @return Price of sale excluding VAT.
     */
    public double getPrice() {
        return this.price;
    }

    /**
     * Returns VAT amount of sale.
     * @return Total VAT amount of sale.
     */
    public double getVatAmount() {
        return this.vatAmount;
    }

    /**
     * Returns the total price of the sale, including VAT.
     * @return Price of sale including VAT.
     */
    public double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Returns what amount the customer needs to pay (<code>totalPrice</code> rounded).
     * @return Amount to pay.
     */
    public int getAmountToPay() {
        return this.amountToPay;
    }

    /**
     * Returns the amount paid by the customer for the purchase.
     * @return Amount paid by customer.
     */
    public int getAmountPaid() {
        return this.amountPaid;
    }

    /**
     * Returns change amount from sale.
     * @return Change paid back to customer.
     */
    public int getChangeAmount() {
        return this.changeAmount;
    }
}
