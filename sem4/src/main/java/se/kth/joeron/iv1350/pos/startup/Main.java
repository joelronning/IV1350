package se.kth.joeron.iv1350.pos.startup;

import se.kth.joeron.iv1350.pos.controller.Controller;
import se.kth.joeron.iv1350.pos.integration.IOController;
import se.kth.joeron.iv1350.pos.integration.DBController;
import se.kth.joeron.iv1350.pos.view.View;

/**
 * This class is responsible for starting up the components of the POS program.
 */
public final class Main {
    /**
     * Creates the program components and runs the program.
     * @param args The arguments of the program.
     */
    public static void main(String[] args) {
        System.out.println("Point of Sale program running...");
        IOController ioController = new IOController();
        DBController externalSystems = new DBController();
        Controller controller = new Controller(externalSystems, ioController);
        View view = new View(controller);

        testRun(view);
    }

    private static void testRun(View view) {
        view.startNewSaleDummy();
        view.registerItemDummy(3);
        view.registerItemDummy(1);
        view.registerItemDummy(3);
        view.registerItemDummy(404);
        view.registerItemDummy(3);
        view.registerItemDummy(66);
        view.registerItemDummy(4);
        view.endSaleDummy();
        view.registerPaymentDummy(150);
        view.cleanUp();
    }
}
