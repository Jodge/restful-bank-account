package com.example.webservice.rest.models;

/**
 * @author George Otieno
 *
 */
public class UserTransaction {
    
    private double amount;
    
    public UserTransaction() {}
    
    public UserTransaction(double amount) {
    	this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
    
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
