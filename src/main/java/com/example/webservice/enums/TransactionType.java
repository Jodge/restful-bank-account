package com.example.webservice.enums;

/**
 * @author George Otieno
 *
 */
public enum TransactionType {
    
    DEPOSIT(1), WITHDRAWAL(2);
    int id;
    
    private TransactionType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    

}
