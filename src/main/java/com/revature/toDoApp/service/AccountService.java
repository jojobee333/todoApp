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
import java.util.Optional;

@Service
public class AccountService {


    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountValidator accountValidator;

    public Optional<Account> findByName(String name) {
        return Optional.ofNullable(accountRepository.findByName(name))
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found."));
    }


    public Optional<Account> createAccount(Account account){
        // Validate data in Account Object is valid
        Errors errors = new BeanPropertyBindingResult(account, "account");
        accountValidator.validate(account, errors);
        if(errors.hasErrors()){
            throw new InvalidAccountException("Account is Invalid.", errors);
        }
        // Does account exist? If so, throw an error.
        findByName(account.getName()).ifPresent(a -> {
            throw new InvalidAccountException("An account already exists with that username.");
        });
        return Optional.of(accountRepository.save(account));

    }

    public Optional<List<Account>> getAllAccounts(){
        return Optional.of(accountRepository.findAll());
    }



}
