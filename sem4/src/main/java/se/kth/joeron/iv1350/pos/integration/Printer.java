package se.kth.joeron.iv1350.pos.integration;

import java.time.format.DateTimeFormatter;

import se.kth.joeron.iv1350.pos.model.Receipt;

/**
 * This class is responsible for communicating with the external receipt
 * printer. The printer is currently a dummy printer, writing to System.out.
 */
public class Printer {
    /**
     * Creates a new instance.
     */
    Printer(){}
    
    /**
     * Send a printout request to external printer.
     * @param currentReceipt Receipt object with information about current sale.
     */
    public void printReceipt(Receipt currentReceipt) {
        String printOut = this.formatReceipt(currentReceipt);

        this.dummyPrinter(printOut);
    }

    private String formatReceipt(Receipt receipt) {
        StringBuilder sb = new StringBuilder("Receipt\n");
        DateTimeFormatter dateAndTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sb.append(receipt.getDateAndTime().format(dateAndTimeFormatter));
        this.newSection(sb);
       
        sb.append(String.format("%-21s %3s %11s%n", "Item", "Qty", "Price"));
        sb.append(receipt.getItems());
        this.newSection(sb);

        sb.append("Total price: ");
        sb.append(receipt.getPrice());
        this.addNewline(sb);
        sb.append("VAT: ");
        sb.append(receipt.getVATAmount());
        this.addNewline(sb);
        sb.append("Amount to pay: ");
        sb.append(receipt.getAmountToPay());
        this.newSection(sb);

        sb.append("Payment: ");
        sb.append(receipt.getAmountPaid());
        this.addNewline(sb);
        sb.append("Change: ");
        sb.append(receipt.getChangeAmount());
        this.addNewline(sb);

        return sb.toString();
    }

    private void addNewline(StringBuilder sb) {
        sb.append(String.format("%n"));        
    }

    private void newSection(StringBuilder sb) {
        sb.append(String.format("%n%n"));        
    }

    private void dummyPrinter(String printOut) {
        System.out.println("------------------------\"Dummy printer\"------------------------");
        System.out.print(printOut);
        System.out.println("----------------------End \"dummy\" printer----------------------\n");
    }
}
