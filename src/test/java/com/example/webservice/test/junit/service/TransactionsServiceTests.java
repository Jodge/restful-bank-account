package com.example.webservice.test.junit.service;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.webservice.domain.AccountTransaction;
import com.example.webservice.enums.TransactionType;
import com.example.webservice.services.TransactionsService;
import com.example.webservice.shared.utils.AccountUtils;

import static org.assertj.core.api.Assertions.*;

/**
 * @author George Otieno
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransactionsServiceTests {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired 
	TransactionsService transactionsService;
	
	@Test
	public void testFindByDateBetweenAndType() {
		this.entityManager.persist(new AccountTransaction(TransactionType.WITHDRAWAL.getId(), 1000, new Date()));
		List<AccountTransaction> transactions = transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()), AccountUtils.getEndOfDay(new Date()), TransactionType.WITHDRAWAL.getId());
		assertThat(transactions.get(0)).isNotNull();
		assertThat(transactions.get(0).getType()).isEqualTo(TransactionType.WITHDRAWAL.getId());
		assertThat(transactions.get(0).getAmount()).isEqualTo(1000);
		assertThat(transactions.get(0).getDate()).isBetween(AccountUtils.getStartOfDay(new Date()), AccountUtils.getEndOfDay(new Date()));
	}

}
