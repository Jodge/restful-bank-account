package com.example.webservice.services;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.webservice.domain.AccountTransaction;

/**
 * @author George Otieno
 *
 */
@Repository
public interface TransactionsService extends CrudRepository<AccountTransaction, Long>{
	
	List<AccountTransaction> findByDateBetweenAndType(Date StartOfDay, Date endOfDay, int type);

}
