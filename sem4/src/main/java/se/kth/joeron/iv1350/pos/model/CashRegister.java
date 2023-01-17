package se.kth.joeron.iv1350.pos.model;

import java.util.ArrayList;

/**
 * This class is responsible for handling the cash register, which is
 * an internal system of the program.
 */
public class CashRegister {
    private int totalRevenue;
    private ArrayList<TotalRevenueObserver> observers = new ArrayList<TotalRevenueObserver>();
    
    /**
     * Creates a new instance.
     * @param initialBalance The starting amount of cash in the cash register.
     */
    public CashRegister(int initialBalance) {
        this.totalRevenue = initialBalance;
    }

    /**
     * Creates a new instance.
     * @param initialBalance The starting amount of cash in the cash register.
     * @param observers List of observers to notify when total revenue is updated
     */
    public CashRegister(int initialBalance, ArrayList<TotalRevenueObserver> observers) {
        this.totalRevenue = initialBalance;
        this.observers = observers;
    }

    /**
     * Updates the balance with incoming payment.
     * @param amount Payment amount to add to the total balance.
     */
    public void addPayment(int amount) {
        this.totalRevenue += amount;
        this.notifyObservers();
    }

    /**
     * Adds a new observer to the list of observers that are notified when the
     * total revenue changes.
     * @param observer Observer to be added.
     */
    public void addObserver(TotalRevenueObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObservers() {
        for (TotalRevenueObserver obs : this.observers)
            obs.updateTotalRevenue(this.totalRevenue);
    }
}
