package com.tala.webservice.web.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tala.webservice.domain.Customer;
import com.tala.webservice.services.CustomerRepository;
import com.tala.webservice.shared.web.StandardJsonResponse;
import com.tala.webservice.shared.web.StandardJsonResponseImpl;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController extends BaseController {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody StandardJsonResponse getCustomer(HttpServletRequest request, HttpServletResponse response, HttpSession session, @PathVariable Long id) {
		
		StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
		HashMap<String, Object> responseData = new HashMap<>();
		
		try {
			Customer customer = customerRepository.findOne(id);
			
			if (customer == null) {
				jsonResponse.setSuccess(false, "Resource not found", StandardJsonResponse.RESOURCE_NOT_FOUND_MSG);
				jsonResponse.setHttpResponseCode(HttpStatus.SC_NO_CONTENT);
				return jsonResponse;
			}
			
			responseData.put("firstName", customer.getFirstName());
			responseData.put("lastName", customer.getLastName());
			
			jsonResponse.setSuccess(true);
			jsonResponse.setData(responseData);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
			return jsonResponse;
			
		} catch (Exception e) {
			logger.error("exception", e);
			jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE, StandardJsonResponse.DEFAULT_MSG_NAME_VALUE);
			jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
			return jsonResponse;
		}
		
	}

}
