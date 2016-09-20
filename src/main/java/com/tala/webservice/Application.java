package com.tala.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tala.webservice.domain.Customer;
import com.tala.webservice.services.CustomerRepository;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class.getName());
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Thomas", "Edison"));
			repository.save(new Customer("Mark", "Williams"));
			repository.save(new Customer("John", "Doe"));
			repository.save(new Customer("Taylor", "Swift"));
			
			// fetch all customers
			log.info("------------------------------");
			for(Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			
			// fetch an individual customer by ID
			Customer customer = repository.findOne(1L);
			log.info("------------------------------");
			log.info(customer.toString());
			log.info("");
			
			// fetch customers by last name
			for (Customer bauer : repository.findByLastName("Bauer")) {
				log.info("------------------------------");
				log.info(bauer.toString());
			}
			
		};
	}
}
