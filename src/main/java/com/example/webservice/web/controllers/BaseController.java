package com.example.webservice.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.webservice.services.AccountService;
import com.example.webservice.services.TransactionsService;

/**
 * @author George Otieno
 *
 */
public class BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
    
    protected static final long ACCOUNT_ID = 1L;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    TransactionsService transactionsService; 

}
