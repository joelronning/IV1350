package se.kth.joeron.iv1350.pos.model;

public class CashRegister {
    private int balance;
    
    /**
     * Creates a new instance.
     * @param initialBalance The starting amount of cash in the cash register.
     */
    public CashRegister(int initialBalance) {
        this.balance = initialBalance;
    }

    public void addPayment(int amount) {
        this.balance += amount;
    }
}
