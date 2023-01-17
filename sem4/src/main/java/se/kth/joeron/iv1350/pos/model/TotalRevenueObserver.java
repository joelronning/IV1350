package se.kth.joeron.iv1350.pos.model;

/**
 * This interface is used to implement an observer pattern for showing the
 * total revenue.
 */
public interface TotalRevenueObserver {
    /**
     * Informs observer about the updated total revenue.
     * @param totalRevenue The updated total revenue amount.
     */
    public void updateTotalRevenue(int totalRevenue);
}
