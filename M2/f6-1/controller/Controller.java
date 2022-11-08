package controller;
import model.Account;

public class Controller {
    private Account acct;
    
    public void deposit(int amount) {
        acct.deposit(amount);
    }
}
