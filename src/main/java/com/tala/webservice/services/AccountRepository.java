package com.tala.webservice.services;

import org.springframework.data.repository.CrudRepository;

import com.tala.webservice.domain.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{

}
