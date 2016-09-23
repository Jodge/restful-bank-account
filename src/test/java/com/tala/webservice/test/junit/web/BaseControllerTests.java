package com.tala.webservice.test.junit.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.tala.webservice.services.AccountService;
import com.tala.webservice.services.TransactionsService;

public class BaseControllerTests {
	
	@Autowired
    protected MockMvc mvc;

    @MockBean
    protected AccountService accountService;
    
    @MockBean
    protected TransactionsService transactionsService;

}
