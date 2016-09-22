package com.tala.webservice.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tala.webservice.domain.AccountTransaction;

public interface TransactionsRepository extends CrudRepository<AccountTransaction, Long>{
	
	List<AccountTransaction> findByDateBetween(Date StartOfDay, Date endOfDay);
	
	List<AccountTransaction> findByDateBetweenAndType(Date StartOfDay, Date endOfDay, int type);

}
