package com.tala.webservice.test.junit.service;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tala.webservice.domain.Account;
import com.tala.webservice.services.AccountService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired 
	AccountService accountService;
	
	@Test
	public void testFindOne() {
		this.entityManager.persist(new Account(400));
		Account account = this.accountService.findOne(1L);
		assertThat(account.getAmount()).isEqualTo(400);
		
	}
	
}

