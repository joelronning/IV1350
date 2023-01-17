package se.kth.joeron.iv1350.pos.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import se.kth.joeron.iv1350.pos.dto.ItemDTO;
import se.kth.joeron.iv1350.pos.exception.InventorySystemException;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;

public class DBControllerTest {
    DBController dbController;
    
    @BeforeEach
    public void setUp() {
        dbController = new DBController();
    }

    @AfterEach
    public void tearDown() {
        dbController = null;
    }

    @Test
    public void testRequestItemInfoSuccess() {
        try {
            ItemDTO result = dbController.requestItemInfo(1);
            
            assertTrue(result.getName().equals("Banana"), "Name of item is not correct");
            assertEquals(2.5, result.getPrice(), "Price of item is not correct");
            assertEquals(1, result.getQuantity(), "Quantity of item is not correct");
            assertEquals(0.06, result.getVatRate(), "VAT rate of item is not correct");
        }
        catch (Exception e) {
            fail("Retrieving existing item with ID: 1 failed with error: " + e.getMessage());
        }
    }

    @Test
    public void testRequestItemInfoNonExistentId() {
        try {
            ItemDTO result = dbController.requestItemInfo(1010);
            fail("Could successfully retrieve non-existing item with ID: 1010");
        }
        catch (ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("1010"), "Wrong exception message, does not" +
                        "contain correct item ID number");
            assertEquals(1010, infe.getItemID(), "Wrong exception ID number: " +
                        infe.getItemID());
        }
        catch (InventorySystemException ise) {
            fail("Wrong exception: " + ise.getMessage());
        }
    }

    @Test
    public void testRequestItemInfoNegativeId() {
        try {
            ItemDTO result = dbController.requestItemInfo(-2);
            fail("Could successfully retrieve non-existing item with ID: 1010");
        }
        catch (ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("-2"), "Wrong exception message, does not" +
                        "contain correct item ID number");
            assertEquals(-2, infe.getItemID(), "Wrong exception ID number: " +
                        infe.getItemID());
        }
        catch (InventorySystemException ise) {
            fail("Wrong exception: " + ise.getMessage());
        }
    }
    
    @Test
    public void testRequestItemInfoForceInventorySystemException() {
        try {
            ItemDTO result = dbController.requestItemInfo(66);
            fail("Could not simulate database error");
        }
        catch (InventorySystemException ise) {
            assertTrue(ise.getMessage().contains("database error"), "Wrong exception message: " + ise.getMessage());
        }
        catch (Exception e) {
            fail("Wrong exception: " + e.getMessage());
        }
    }
}
