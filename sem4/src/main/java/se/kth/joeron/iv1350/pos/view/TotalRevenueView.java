package se.kth.joeron.iv1350.pos.view;

import se.kth.joeron.iv1350.pos.model.TotalRevenueObserver;

/**
 * Shows total revenue to the user inteface.
 */
public class TotalRevenueView implements TotalRevenueObserver {
    
    /**
     * Displays new total revenue to user interface (<code>System.out</code>).
     * @param totalRevenue The updated total revenue amount.
     */
    public void updateTotalRevenue(int totalRevenue) {
        StringBuilder totalRevenueMessageSB = new StringBuilder();
        totalRevenueMessageSB.append("-----------TotalRevenueView-----------\n");
        totalRevenueMessageSB.append("Total revenue: ");
        totalRevenueMessageSB.append(totalRevenue);
        totalRevenueMessageSB.append("\n-----------TotalRevenueView-----------");
        System.out.println(totalRevenueMessageSB);
    }
}
