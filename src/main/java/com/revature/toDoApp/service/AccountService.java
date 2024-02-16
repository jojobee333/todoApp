package com.revature.toDoApp.service;

import com.revature.toDoApp.dto.AccountDTO;
import com.revature.toDoApp.dto.TodoDTO;
import com.revature.toDoApp.exception.InvalidAccountException;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.validator.AccountValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountValidator.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TodoService todoService;
    @Autowired
    private AccountValidator accountValidator;


    // Convert DTO to Entity
    public Account convertToEntity(AccountDTO accountDto) {
        Account account = new Account();
        account.setAccountId(accountDto.getAccountId());
        account.setPassword(accountDto.getPassword());
        account.setAccountName(accountDto.getAccountName());
        return account;
    }

    public AccountDTO convertToDto(Account account) {

        AccountDTO accountDto = new AccountDTO();
        accountDto.setAccountId(account.getAccountId());
        accountDto.setAccountName(account.getAccountName());
        accountDto.setPassword("********");
        List<TodoDTO> todoDtoList = new ArrayList<>();
        for(Todo todo: account.getTodoList()){
            TodoDTO newtodoDto = new TodoDTO(
                    todo.getText(), todo.getCompleted(), todo.getAccount().getAccountName());
            todoDtoList.add(newtodoDto);
        }
        logger.info(account.getTodoList().toString());
        accountDto.setTodoList(todoDtoList);
        return accountDto;
    }
    private void validateAccountDTO(AccountDTO account) {
        Errors errors = new BeanPropertyBindingResult(account, "accountDTO");
        accountValidator.validate(account, errors);
        if (errors.hasErrors()) {
            throw new InvalidAccountException("Account Data Class Object is Invalid.", errors);
        }
    }



    private void validateAccount(Account account) {
        Errors errors = new BeanPropertyBindingResult(account, "account");
        accountValidator.validate(account, errors);
        if (errors.hasErrors()) {
            throw new InvalidAccountException("Account is Invalid.", errors);
        }
    }

    public AccountDTO getAccountByName(String name) {
        Account account = accountRepository.findByName(name)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found."));
        return convertToDto(account);
    }

    public AccountDTO createAccount(Account account) {
        logger.info(account.toString());
        validateAccount(account);
        accountRepository.findByName(account.getAccountName()).ifPresent(a -> {
            throw new InvalidAccountException("An account already exists with that username.");
        });
        Account savedAccount = accountRepository.save(account);
        return convertToDto(savedAccount);
    }

    public Boolean deleteAccount(Integer accountId) {
        return accountRepository.findById(accountId)
                .map(account -> {
                    accountRepository.deleteById(accountId);
                    return true;
                })
                .orElse(false);
    }

    public List<AccountDTO> getAllAccounts() {
        return accountRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


}
