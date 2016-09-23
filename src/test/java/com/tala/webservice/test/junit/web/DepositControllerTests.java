package com.tala.webservice.test.junit.web;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;
import com.tala.webservice.domain.AccountTransaction;
import com.tala.webservice.enums.TransactionType;
import com.tala.webservice.rest.models.UserTransaction;
import com.tala.webservice.shared.utils.AccountUtils;
import com.tala.webservice.web.controllers.DepositController;

@RunWith(SpringRunner.class)
@WebMvcTest(DepositController.class)
public class DepositControllerTests extends BaseControllerTests {
    
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
    public void testDepositExceedsMaxDepositPerTransaction() {
    	
    }
    
    @Test
    public void testTransactionsExceedsMaxAllowedPerDay() {
    	
    }
    
    @Test
    public void testSuccessfullDepositTransaction() {
    	
    }

}
