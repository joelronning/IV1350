package se.kth.joeron.iv1350.pos.model;

import se.kth.joeron.iv1350.pos.dto.ItemDTO;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class ItemTest {
    ItemDTO itemInfo, itemInfo2;
    Item itemToTest, itemToTest2;

    @BeforeEach
    public void setUp() {
        itemInfo = new ItemDTO(11,
                            "Banana",
                            "Origin: Colombia",
                            2.50,
                            1,
                            0.06);
        itemInfo2 = new ItemDTO(22,
                            "Lightbuld",
                            "Energy efficient LED lightbulb",
                            54.50,
                            1,
                            0.25);
        itemToTest = new Item(itemInfo);
        itemToTest2 = new Item(itemInfo2);
    }

    @AfterEach
    public void tearDown() {
        itemInfo = null;
        itemToTest = null;
    }

    @Test
    public void testGetPrice() {
        double expectedResult = 2.50;
        double result = itemToTest.getPrice();
        assertEquals(expectedResult, result, "Price does not match");
    }

    @Test
    public void testGetPriceQuantity5() {
        itemToTest.changeQuantity(5);
        double expectedResult = 2.50 * 5;
        double result = itemToTest.getPrice();
        assertEquals(expectedResult, result, "Price does not match");
    }
   
    @Test
    public void testGetQuantity() {
        assertEquals(1, itemToTest.getQuantity(), "Quantity does not match");
    }

    @Test
    public void testIncreaseQuantityBy1() {
        itemToTest.increaseQuantityBy1();
        double expectedResult = 2;
        double result = itemToTest.getQuantity();
        assertEquals(expectedResult, result, "Price does not match");
    }

    @Test
    public void testChangeQuantity() {
        itemToTest.changeQuantity(4);
        double expectedResult = 4;
        double result = itemToTest.getQuantity();
        assertEquals(expectedResult, result, "Price does not match");
    }

    @Test
    public void testGetID() {
        int result = itemToTest.getID();
        assertEquals(11, result, "ID does not match");
    }

    @Test
    public void testGetName() {
        assertEquals("Banana", itemToTest.getName(), "Name does not match");
    }

    @Test
    public void testGetVATAmount() {
        double exptectedResult = 2.50 * 0.06;
        double result = itemToTest.getVATAmount();
        assertEquals(exptectedResult, result, "VAT amount does not match");
    }

    @Test
    public void testGetVATAmountQuantity5() {
        itemToTest.changeQuantity(5);
        double expectedResult = 2.50 * 0.06 * 5;
        double result = itemToTest.getVATAmount();
        assertEquals(expectedResult, result, "VAT amount does not match");
    }

    @Test
    public void testGetVATAmountDifferentRate() {
        double expectedResult = 54.50 * 0.25;
        double result = itemToTest2.getVATAmount();
        assertEquals(expectedResult, result, "VAT amount does not match");
    }
}
