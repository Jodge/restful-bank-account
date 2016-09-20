package com.tala.webservice.services;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.tala.webservice.domain.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
	
	List<Customer> findByLastName(String lastName);

}
