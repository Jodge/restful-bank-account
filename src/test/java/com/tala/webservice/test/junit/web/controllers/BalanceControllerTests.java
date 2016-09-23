package com.tala.webservice.test.junit.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.tala.webservice.domain.Account;
import com.tala.webservice.services.AccountService;
import com.tala.webservice.web.controllers.BalanceController;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BalanceController.class)
public class BalanceControllerTests {
	
	@Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;
    
    @Test
    public void testGetBalance() throws Exception {
        given(this.accountService.findOne(1L))
                .willReturn(new Account(400));
        this.mvc.perform(get("/balance/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":true,\"messages\":{},\"errors\":{},\"data\":{\"balance\":\"$400.0\"},\"httpResponseCode\":200}"));
    }

}
