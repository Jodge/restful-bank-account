package com.tala.webservice.services;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tala.webservice.domain.Account;

/**
 * @author George Otieno
 *
 */
@Repository
public interface AccountService extends CrudRepository<Account, Long>{
    
    Account findOne(Long id);

}
