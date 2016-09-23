package com.tala.webservice.test.junit.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tala.webservice.web.controllers.DepositController;

@RunWith(SpringRunner.class)
@WebMvcTest(DepositController.class)
public class WithdrawalControllerTests extends BaseControllerTests {
	
	@Test
	public void testMaxWithdrawalForTheDayIsReached() throws Exception {
		
	}
	
	@Test
	public void testMaxWithdrawalPerTransaction() throws Exception {
		
	}

}
