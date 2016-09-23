package com.tala.webservice.test.junit.web.controllers;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;
import com.tala.webservice.domain.AccountTransaction;
import com.tala.webservice.enums.TransactionType;
import com.tala.webservice.rest.models.UserTransaction;
import com.tala.webservice.services.AccountService;
import com.tala.webservice.services.TransactionsService;
import com.tala.webservice.shared.utils.AccountUtils;
import com.tala.webservice.web.controllers.DepositController;

@RunWith(SpringRunner.class)
@WebMvcTest(DepositController.class)
public class DepositControllerTests {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;
    
    @MockBean
    private TransactionsService transactionsService;
    
    @Test
    public void testMaxDepositForTheDayIsReached() throws Exception {
    	AccountTransaction transaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), 100000, new Date());
    	AccountTransaction transaction2 = new AccountTransaction(TransactionType.DEPOSIT.getId(), 100000, new Date());
    	
    	List<AccountTransaction> list = new ArrayList<>();
    	list.add(transaction);
    	list.add(transaction2);
    	
    	UserTransaction userTransaction = new UserTransaction(5000);
    	Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
    	
    	given(this.transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId())).willReturn(list);
        this.mvc.perform(post("/deposit/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":false,\"messages\":{\"message\":\"Deposit for the day should not be more than $150K\",\"title\":\"Error\"},\"errors\":{},\"data\":{},\"httpResponseCode\":406}"));
    }
    
    @Test
    public void testDepositExceedsMaxDepositPerTransaction() throws Exception {
        AccountTransaction transaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), 50000, new Date());
        AccountTransaction transaction2 = new AccountTransaction(TransactionType.DEPOSIT.getId(), 50000, new Date());
        
        List<AccountTransaction> list = new ArrayList<>();
        list.add(transaction);
        list.add(transaction2);
        
        UserTransaction userTransaction = new UserTransaction(50000); // deposit of $50K
        Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
        
        given(this.transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId())).willReturn(list);
        this.mvc.perform(post("/deposit/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":false,\"messages\":{\"message\":\"Deposit per transaction should not be more than $40K\",\"title\":\"Error\"},\"errors\":{},\"data\":{},\"httpResponseCode\":406}"));
    }
    
    @Test
    public void testTransactionsExceedsMaxAllowedPerDay() throws Exception {
        AccountTransaction transaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), 100000, new Date());
        AccountTransaction transaction2 = new AccountTransaction(TransactionType.DEPOSIT.getId(), 10000, new Date());
        AccountTransaction transaction3 = new AccountTransaction(TransactionType.DEPOSIT.getId(), 5000, new Date());
        AccountTransaction transaction4 = new AccountTransaction(TransactionType.DEPOSIT.getId(), 200, new Date());
        
        List<AccountTransaction> list = new ArrayList<>();
        list.add(transaction);
        list.add(transaction2);
        list.add(transaction3);
        list.add(transaction4);
        
        UserTransaction userTransaction = new UserTransaction(5000); // $5K deposit
        Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
        
        given(this.transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId())).willReturn(list);
        this.mvc.perform(post("/deposit/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":false,\"messages\":{\"message\":\"maximum transactions for the day Exceeded\",\"title\":\"Error\"},\"errors\":{},\"data\":{},\"httpResponseCode\":406}"));
    }
    
    @Ignore
    @Test
    public void testSuccessfullDepositTransaction() throws Exception {
        AccountTransaction transaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), 50000, new Date());
        
        List<AccountTransaction> list = new ArrayList<>();
        list.add(transaction);
        
        UserTransaction userTransaction = new UserTransaction(5000); // $5K deposit
        Gson gson = new Gson();
        String json = gson.toJson(userTransaction);
        
        given(this.transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId())).willReturn(list);
        given(this.transactionsService.save(transaction)).willReturn(transaction);
        //given(this.accountService.findOne(1L)).willReturn(new Account(50000));
        //given(this.accountService.save(new Account(50000)));
        this.mvc.perform(post("/deposit/").contentType(MediaType.APPLICATION_JSON).content(json))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":true,\"messages\":{\"message\":\"Deposit sucessfully Transacted\",\"title\":\"\"},\"errors\":{},\"data\":{},\"httpResponseCode\":200}"));
    }

}
