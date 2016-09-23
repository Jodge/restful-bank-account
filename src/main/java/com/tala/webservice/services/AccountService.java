package com.tala.webservice.services;

import org.springframework.data.repository.CrudRepository;

import com.tala.webservice.domain.Account;

/**
 * @author George Otieno
 *
 */
public interface AccountService extends CrudRepository<Account, Long>{
    
    Account findOne(Long id);

}
