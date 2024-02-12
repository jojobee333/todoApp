package com.revature.toDoApp.service;


import com.revature.toDoApp.exception.InvalidAccountException;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.validator.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;


@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountValidator accountValidator;


    private void validateAccount(Account account){
        Errors errors = new BeanPropertyBindingResult(account, "account");
        accountValidator.validate(account, errors);
        if(errors.hasErrors()){
            throw new InvalidAccountException("Account is Invalid.", errors);
        }

    }

    public Account getAccountByName(String name) {
        return accountRepository.findByName(name)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found."));
    }


    public Account createAccount(Account account){
        // Validate data in Account Object is valid
       validateAccount(account);
        // Check if account with the same name already exists
        accountRepository.findByName(account.getName()).ifPresent(a -> {
            throw new InvalidAccountException("An account already exists with that username.");
        });
        // Save and return the new account
        return accountRepository.save(account);

    }


    public Boolean deleteAccount(Integer account_id){
        return accountRepository.findById(account_id)
                .map(account -> {
                    accountRepository.deleteById(account_id);
                    return true;
                })
                .orElse(false);

    }

    public List<Account> getAllAccounts(){
        return accountRepository.findAll();
    }



}
