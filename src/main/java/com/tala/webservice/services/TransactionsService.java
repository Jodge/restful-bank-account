package com.tala.webservice.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.tala.webservice.domain.AccountTransaction;

/**
 * @author George Otieno
 *
 */
public interface TransactionsService extends CrudRepository<AccountTransaction, Long>{
	
	List<AccountTransaction> findByDateBetweenAndType(Date StartOfDay, Date endOfDay, int type);

}
