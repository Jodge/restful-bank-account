package com.tala.webservice.web.controllers;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tala.webservice.domain.Account;
import com.tala.webservice.services.AccountService;
import com.tala.webservice.shared.web.StandardJsonResponse;
import com.tala.webservice.shared.web.StandardJsonResponseImpl;

@RestController
@RequestMapping(value="/balance")
public class BalanceController extends BaseController { 
    
    @Autowired
    AccountService accountService;
    
    @RequestMapping(value="/", method = RequestMethod.GET)
    public @ResponseBody StandardJsonResponse getBalance(HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        
        StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
        HashMap<String, Object> responseData = new HashMap<>();
        
        try {
            Account account = accountService.findOne(ACCOUNT_ID);
            
            if (account != null) {
                responseData.put("balance", "$"+ account.getAmount());
                
                jsonResponse.setSuccess(true);
                jsonResponse.setData(responseData);
                jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
            } else {
                jsonResponse.setSuccess(false, "Resource not found", StandardJsonResponse.RESOURCE_NOT_FOUND_MSG);
                jsonResponse.setHttpResponseCode(HttpStatus.SC_NO_CONTENT);
            }
            
        } catch (Exception e) {
            logger.error("exception", e);
            jsonResponse.setSuccess(false, StandardJsonResponse.DEFAULT_MSG_TITLE_VALUE, StandardJsonResponse.DEFAULT_MSG_NAME_VALUE);
            jsonResponse.setHttpResponseCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            return jsonResponse;
        }
        
        return jsonResponse;
    }

}
