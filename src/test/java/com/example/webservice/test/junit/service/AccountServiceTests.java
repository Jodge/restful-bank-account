package com.example.webservice.test.junit.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.Account;
import com.example.webservice.services.AccountService;

/**
 * @author George Otieno
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountServiceTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired 
	AccountService accountService;
	
	@Test
	public void testFindOne() {
		this.entityManager.persist(new Account(400.0));
		this.accountService.findAll(); 
		Account account = this.accountService.findOne(2L); // ID 2 because default account 1 is created whenever the program runs
		// see Application.init
		assertThat(account.getAmount()).isEqualTo(400.0);
		
	}
	
}

