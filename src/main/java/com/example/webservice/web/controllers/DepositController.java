package com.example.webservice.web.controllers;

import java.util.Date;
import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webservice.domain.Account;
import com.example.webservice.domain.AccountTransaction;
import com.example.webservice.enums.TransactionType;
import com.example.webservice.shared.utils.AccountUtils;
import com.example.webservice.shared.web.StandardJsonResponse;
import com.example.webservice.shared.web.StandardJsonResponseImpl;

/**
 * @author George Otieno
 *
 */
@RestController
@RequestMapping("/deposit")
public class DepositController extends BaseController {
    
    private static final double MAX_DEPOSIT_PER_TRANSACTION  = 40000; // $40k
    private static final double MAX_DEPOSIT_PER_DAY = 150000; // $150k
    private static final int MAX_DEPOSIT_TRANSACTIONS_PER_DAY = 4;
    
    @RequestMapping(value="/", method = RequestMethod.POST)
    public @ResponseBody StandardJsonResponse makeDeposit(@RequestBody com.example.webservice.rest.models.UserTransaction userTransaction) {
        
        StandardJsonResponse jsonResponse = new StandardJsonResponseImpl();
        
        try {
            
            double total = 0;
            
            // check maximum limit deposit for the day has been reached
            List<AccountTransaction> deposits  = transactionsService.findByDateBetweenAndType(AccountUtils.getStartOfDay(new Date()),
                    AccountUtils.getEndOfDay(new Date()), TransactionType.DEPOSIT.getId());
            
            if (deposits.size() > 0) {
                for (AccountTransaction accountTransaction: deposits) {
                    total+=accountTransaction.getAmount(); 
                }
                if (total  + userTransaction.getAmount()  > MAX_DEPOSIT_PER_DAY) {
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
            
            // check whether transactions exceeds the max allowed per day
            if (deposits.size() < MAX_DEPOSIT_TRANSACTIONS_PER_DAY) {
                AccountTransaction accountTransaction = new AccountTransaction(TransactionType.DEPOSIT.getId(), userTransaction.getAmount(), new Date());
                double amount  = transactionsService.save(accountTransaction).getAmount();
                
                Account account = accountService.findOne(ACCOUNT_ID);
                double newBalance = account.getAmount() + amount;
                account.setAmount(newBalance);
                accountService.save(account);
                
                jsonResponse.setSuccess(true, "", "Deposit sucessfully Transacted");
                jsonResponse.setHttpResponseCode(HttpStatus.SC_OK);
                
            } else {
                jsonResponse.setSuccess(false, "Error", "maximum transactions for the day Exceeded");
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
