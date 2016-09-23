package com.tala.webservice.web.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tala.webservice.services.AccountService;
import com.tala.webservice.services.TransactionsService;

public class BaseController {
	
	protected final Log logger = LogFactory.getLog(getClass());
    
    protected static final long ACCOUNT_ID = 1L;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    TransactionsService transactionsService; 

}
