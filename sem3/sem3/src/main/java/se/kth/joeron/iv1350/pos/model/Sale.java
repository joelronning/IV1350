package se.kth.joeron.iv1350.pos.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import se.kth.joeron.iv1350.pos.dto.DiscountRulesDTO;
import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.dto.SaleDTO;

public class Sale {
    private LocalDateTime dateAndTime;
    private List<Item> items;
    private double price;
    private double vatAmount;
    private double totalPrice;
    private int amountPaid;
    private int change;

    public Sale() {
        this.items = new ArrayList<Item>();
        this.price = 0;
    }

    /**
     * Creates a new item and adds it to <code>items</code> or if item of the same ID exists, 
     * increases the <code>quantity</code> of that item by 1.
     * @param itemInformation DTO containg information about item to be added.
     * @return DTO with current state of <code>Sale</code> instance.
     */
    public SaleDTO registerItem(ItemDTO itemInformation) {
        int itemIndex = this.itemExists(itemInformation);
        if (itemIndex > -1) {
            this.items.get(itemIndex).increaseQuantityBy1();
        }
        else {
            this.items.add(new Item(itemInformation));
        }

        this.calculatePrice();
        return new SaleDTO(itemListToString(), this.dateAndTime,
                           this.price, this.vatAmount,
                           this.totalPrice, this.calculateAmountToPay(),
                           this.amountPaid, this.change);
    }

    //public SaleDTO enterQuantity(int quantity) {
    //    // TODO
    //}

    //public SaleDTO applyDiscount(DiscountRulesDTO discount) {
    //    // TODO
    //}

    /**
     * Returns information about this sale. Useful for showing price etc.
     * @return DTO with information about this <code>Sale</code> instance.
     */
    public SaleDTO endSale() {
        return new SaleDTO(itemListToString(), this.dateAndTime,
                           this.price, this.vatAmount,
                           this.totalPrice, this.calculateAmountToPay(),
                           this.amountPaid, this.change);
    }

    /**
     * Registers payment and returns sale information post payment.
     * @param amount Amount paid by the customer.
     * @return Information about the sale.
     */
    public SaleDTO registerPayment(int amount) {
        this.amountPaid = amount;
        this.change = this.amountPaid - this.calculateAmountToPay();
        this.dateAndTime = java.time.LocalDateTime.now();
        return new SaleDTO(itemListToString(), this.dateAndTime,
                           this.price, this.vatAmount,
                           this.totalPrice, this.calculateAmountToPay(),
                           this.amountPaid, this.change);
    }

    private int itemExists(ItemDTO itemInformation) {
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).getID() == itemInformation.getID()) {
                return i;
            }
        }
        return -1;
    }

    private void calculatePrice() {
        double sumPrice = 0;
        double sumVAT = 0;
        for (Item item : this.items) {
            sumPrice += item.getPrice();
            sumVAT += item.getVATAmount();
        }
        
        this.price = sumPrice;
        this.vatAmount = sumVAT;
        this.totalPrice = sumPrice + sumVAT;
    }

    private int calculateAmountToPay() {
        return (int)(this.totalPrice + 0.5);
    }

    private String itemListToString() {
        boolean first = true;
        StringBuilder sb = new StringBuilder();
        for (Item item : this.items) {
            if (first)
                first = false;
            else
                sb.append(String.format("%n"));

            sb.append(String.format("%-21s", item.getName()));
            sb.append(String.format("%4s", item.getQuantity()));
            sb.append(String.format("%12.2f", item.getPrice()));
        }

        return sb.toString();
    }
}