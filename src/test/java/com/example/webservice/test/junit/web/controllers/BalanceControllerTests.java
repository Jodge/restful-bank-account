package com.example.webservice.test.junit.web.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.webservice.domain.Account;
import com.example.webservice.web.controllers.BalanceController;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author George Otieno
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(BalanceController.class)
public class BalanceControllerTests extends BaseControllerTests {
	
	@Autowired
    private MockMvc mvc;
    
    @Test
    public void testGetBalance() throws Exception {
        given(this.accountService.findOne(1L))
                .willReturn(new Account(400));
        this.mvc.perform(get("/balance/").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(content().json("{\"success\":true,\"messages\":{},\"errors\":{},\"data\":{\"balance\":\"$400.0\"},\"httpResponseCode\":200}"));
    }

}
