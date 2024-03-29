package se.kth.joeron.iv1350.pos.model;

import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.dto.SaleDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class SaleTest {
    Sale sale;
    ItemDTO itemBanana;
    ItemDTO itemMilk;
    ItemDTO itemToothbrush;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        itemBanana = new ItemDTO(1, "Banana", "Origin: Colombia", 2.50, 1, 0.06);
        itemMilk = new ItemDTO(2, "Milk 1 liter", "Milk from Swedish cows", 11.90, 1, 0.06);
        itemToothbrush = new ItemDTO(5, "Toothbrush", "Toothbrush extra white 1p", 18.50, 1, 0.12);
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        itemBanana = null;
        itemMilk = null;
        itemToothbrush = null;
    }

    @Test
    public void testRegisterItem() {
        SaleDTO saleInfo = sale.registerItem(itemBanana);
        String itemList = saleInfo.getItemList();
        double expectedPrice = 2.65;
        assertTrue(itemList.contains("Banana"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("1"), "Sale does not contain registered item quantity");
        assertTrue(itemList.contains(Double.toString(expectedPrice)), "Sale does not contain registered item price");
        assertEquals(expectedPrice, saleInfo.getTotalPrice(), "Price is not correct");
    }

    @Test
    public void testRegisterItemSameItemTwice() {
        sale.registerItem(itemMilk);
        SaleDTO saleInfo = sale.registerItem(itemMilk);
        String itemList = saleInfo.getItemList();
        double expectedPrice = 25.228;
        assertTrue(itemList.contains("Milk 1 liter"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("2"), "Sale does not contain registered item quantity");
        assertTrue(itemList.contains("25.23"), "Sale does not contain registered item price");
        assertEquals(expectedPrice, saleInfo.getTotalPrice(), "Price is not correct");
    }

    @Test
    public void testRegisterItemDifferentItems() {
        sale.registerItem(itemBanana);
        SaleDTO saleInfo = sale.registerItem(itemToothbrush);
        String itemList = saleInfo.getItemList();
        double expectedPrice = 2.50 * 1.06 + 18.50 * 1.12;
        assertTrue(itemList.contains("Banana"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("1"), "Sale does not contain registered item quantity");
        assertTrue(itemList.contains("2.65"), "Sale does not contain registered item price");
        assertTrue(itemList.contains("Toothbrush"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("20.72"), "Sale does not contain registered item price");
        assertTrue(expectedPrice - saleInfo.getTotalPrice() < 0.0001, "Price is not correct");
    }

    @Test
    public void testEndSale() {
        sale.registerItem(itemBanana);
        sale.registerItem(itemMilk);
        SaleDTO saleInfo = sale.endSale();
        String itemList = saleInfo.getItemList();
        double expectedPrice = 2.50 * 1.06 + 11.90 * 1.06 ;
        assertTrue(itemList.contains("Banana"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("1"), "Sale does not contain registered item quantity");
        assertTrue(itemList.contains("2.65"), "Sale does not contain registered item price");
        assertTrue(itemList.contains("Milk 1 liter"), "Sale does not contain registered item name");
        assertTrue(itemList.contains("12.61"), "Sale does not contain registered item price");
        assertEquals(expectedPrice, saleInfo.getTotalPrice(), "Price is not correct");
    }

    @Test
    public void testRegisterPayment() {
        sale.registerItem(itemMilk);
        sale.registerItem(itemMilk);
        sale.registerItem(itemToothbrush);
        SaleDTO saleInfo = sale.registerPayment(40);
        LocalDateTime expectedDT = LocalDateTime.now();
        double expectedVAT = 11.90 * 2 * 0.06 + 18.50 * 0.12;
        double expectedTotal = 11.90 * 2 + 18.50 + expectedVAT;
        int expectedAmtToPay = (int)(expectedTotal + 0.5);
        int expectedAmtPaid = 40;
        int expectedChange = (int)(expectedAmtPaid - expectedAmtToPay);
        assertTrue(saleInfo.getItemList().contains("Milk 1 liter"), "First item does not match");
        assertTrue(saleInfo.getItemList().contains("Toothbrush"), "Second item does not match");
       
        assertEquals(expectedDT.getYear(), saleInfo.getDateAndTime().getYear(), "Year does not match");
        assertEquals(expectedDT.getMonthValue(), saleInfo.getDateAndTime().getMonthValue(), "Month does not match");
        assertEquals(expectedDT.getDayOfYear(), saleInfo.getDateAndTime().getDayOfYear(), "Day does not match");

        assertEquals(expectedVAT, saleInfo.getVatAmount(), "VAT amount does not match");
        assertEquals(expectedTotal, saleInfo.getTotalPrice(), "Total price does not match");

        assertEquals(expectedAmtToPay, saleInfo.getAmountToPay(), "Amount to pay does not match");
        assertEquals(expectedAmtPaid, saleInfo.getAmountPaid(), "Amount paid does not match");
        assertEquals(expectedChange, saleInfo.getChangeAmount(), "Change amount does not match");
    }
}
