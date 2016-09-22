package com.tala.webservice.web.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.tala.webservice.domain.Account;
import com.tala.webservice.domain.AccountTransaction;
import com.tala.webservice.enums.TransactionType;
import com.tala.webservice.services.AccountService;
import com.tala.webservice.services.TransactionsService;
import com.tala.webservice.shared.utils.AccountUtils;
import com.tala.webservice.shared.web.StandardJsonResponse;
import com.tala.webservice.shared.web.StandardJsonResponseImpl;

@RestController
@RequestMapping("/deposit")
public class DepositController extends BaseController {
    
    private static final double MAX_DEPOSIT_PER_TRANSACTION  = 40000;
    private static final double MAX_DEPOSIT_PER_DAY = 150000;
    private static final int MAX_DEPOSIT_TRANSACTIONS_PER_DAY = 4;
    
    @Autowired
    AccountService accountService;
    
    @Autowired
    TransactionsService transactionsService; 
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    public @ResponseBody StandardJsonResponse makeDeposit(HttpServletRequest request, HttpServletResponse response, HttpSession session,
            @RequestBody com.tala.webservice.rest.models.UserTransaction userTransaction) {
        
        StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
        
        try {
            
            double total = 0;
            
            // check maximum limit deposit for the day has been reached
            List<AccountTransaction> deposits  = transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                    AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId());
            
            if (deposits.size() > 0) {
                for (AccountTransaction accountTransaction: deposits) {
                    total+=accountTransaction.getAmont(); 
                }
                if (total > MAX_DEPOSIT_PER_DAY) {
                    jsonResponse.setSuccess(false, "Error", "Deposit for the day should not be more than $150K");
                    jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
                    return jsonResponse;
                }
            }
            
            // Check whether the amount being deposited exceeds the MAX_DEPOSIT_PER_TRANSACTION
            if(userTransaction.getAmount() > MAX_DEPOSIT_PER_TRANSACTION) {                
                jsonResponse.setSuccess(false, "Error", "Deposit per transaction should not be more than $40K");
                jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
                return jsonResponse;
            }
            List<AccountTransaction> transactions  = transactionsService.findByDateBetween(AccountUtils.getStartOfDay(new Date()),
                    AccountUtils.getEndOfDay(new Date()));
            
            // check whether transactions exceeds the max allowed per day
            if (transactions.size() < MAX_DEPOSIT_TRANSACTIONS_PER_DAY) {
                AccountTransaction accountTransaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), userTransaction.getAmount(), new Date());
                double amount  = transactionsService.save(accountTransaction).getAmont();
                
                Account account = accountService.findOne(ACCOUNT_ID);
                double newBalance = amount + account.getAmount();
                account.setAmount(newBalance);
                accountService.save(account);
                
                jsonResponse.setSuccess(true, "", "Deposit sucessfully Transacted");
                jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
                
            } else {
                jsonResponse.setSuccess(false, "", "maximum transactions for the day Exceeded");
                jsonResponse.setHttpResponseCode(HttpStatus.SC_NOT_ACCEPTABLE);
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
