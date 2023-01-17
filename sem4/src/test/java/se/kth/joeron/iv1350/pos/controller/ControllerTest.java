package se.kth.joeron.iv1350.pos.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import se.kth.joeron.iv1350.pos.dto.SaleDTO;
import se.kth.joeron.iv1350.pos.exception.ItemNotFoundException;
import se.kth.joeron.iv1350.pos.exception.OperationFailedException;
import se.kth.joeron.iv1350.pos.integration.DBController;
import se.kth.joeron.iv1350.pos.integration.IOController;

public class ControllerTest {
    DBController dbController;
    IOController ioController;
    Controller controller;
    
    @BeforeEach
    public void setUp() {
        dbController = new DBController();
        ioController = new IOController();
        controller = new Controller(dbController, ioController);
        controller.startNewSale();
    }

    @AfterEach
    public void tearDown() {
        dbController = null;
        ioController = null;
        controller = null;
    }

    @Test
    public void testRegisterItemSuccess() {
        try {
            SaleDTO result = controller.registerItem(1);
            
            assertTrue(result.getItemList().contains("Banana"), "Name of item is not correct");
            assertTrue(result.getTotalPrice() - (2.5 * 1.06) < 0.00001, "Price of item is not correct");
            assertTrue(result.getVatAmount() - (2.5 * 0.06) < 0.00001, "Quantity of item is not correct");
        }
        catch (Exception e) {
            fail("Retrieving existing item with ID: 1 failed with error: " + e.getMessage());
        }
    }

    @Test
    public void testRegisterItemNonExistentId() {
        try {
            SaleDTO result = controller.registerItem(1010);
            fail("Could successfully retrieve non-existing item with ID: 1010");
        }
        catch (ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("1010"), "Wrong exception message, does not" +
                        "contain correct item ID number");
            assertEquals(1010, infe.getItemID(), "Wrong exception ID number: " +
                        infe.getItemID());
        }
        catch (OperationFailedException ofe) {
            fail("Wrong exception: " + ofe.getMessage());
        }
    }

    @Test
    public void testRegisterItemNegativeId() {
        try {
            SaleDTO result = controller.registerItem(-2);
            fail("Could successfully retrieve non-existing item with ID: 1010");
        }
        catch (ItemNotFoundException infe) {
            assertTrue(infe.getMessage().contains("-2"), "Wrong exception message, does not" +
                        "contain correct item ID number");
            assertEquals(-2, infe.getItemID(), "Wrong exception ID number: " +
                        infe.getItemID());
        }
        catch (OperationFailedException ofe) {
            fail("Wrong exception: " + ofe.getMessage());
        }
    }
    
    @Test
    public void testRegisterItemForceInventorySystemException() {
        try {
            SaleDTO result = controller.registerItem(66);
            fail("Could not simulate database error");
        }
        catch (OperationFailedException ofe) {
            assertTrue(ofe.getCause().getMessage().contains("database error"),
                        "Wrong exception message: " + ofe.getCause().getMessage());
        }
        catch (Exception e) {
            fail("Wrong exception: " + e.getMessage());
        }
    }
}
