package com.example.webservice;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.webservice.domain.Account;
import com.example.webservice.services.AccountService;

/**
 * @author George Otieno
 *
 */
@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean
	public CommandLineRunner init(AccountService accountService) {
		return (args) -> {
			// create account
			accountService.save(new Account(0));
		};
	}
}
