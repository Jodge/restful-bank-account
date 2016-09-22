package com.tala.webservice.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tala.webservice.domain.Transactions;

public interface TransactionsRepository extends CrudRepository<Transactions, Long>{
	
	List<Transactions> findByDateBetweenAndTransactionType(Date StartOfDay, Date endOfDay, String transactionType);
	

}
