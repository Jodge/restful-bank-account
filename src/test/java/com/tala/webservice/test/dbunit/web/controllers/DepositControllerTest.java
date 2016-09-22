package com.tala.webservice.test.dbunit.web.controllers;

import org.testng.annotations.Test;

import com.tala.webservice.services.AccountRepository;
import com.tala.webservice.services.TransactionsRepository;

public class DepositControllerTest extends BaseControllerTest {
    
    AccountRepository accountRepository;
    TransactionsRepository transactionsRepository;
    
    @Test
    public void testFailureResponseWhenMaxDepositForTheDayExceeded() {
        
    }
    
    @Test
    public void testFailureResponseWhenMaxDepositPerTransacationExceeded() {
        
    }
    
    @Test
    public void testFailureResponseWhenMaxDepositFrequencyExceeded() {
        
    }
    
    @Test
    public void testSuccessResponse() {
        
    }

}
