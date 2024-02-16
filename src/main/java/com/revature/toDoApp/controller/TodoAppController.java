package com.revature.toDoApp.controller;
import com.revature.toDoApp.dto.AccountDTO;
import com.revature.toDoApp.dto.TodoDTO;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.exception.TodoNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.service.AccountService;
import com.revature.toDoApp.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TodoAppController {

    private static final Logger logger = LoggerFactory.getLogger(TodoService.class);




    @Autowired
    private TodoService todoService;
    @Autowired
    private AccountService accountService;


    @Autowired
    public TodoAppController(TodoService todoService, AccountService accountService) {
        this.todoService = todoService;
        this.accountService = accountService;

    }

    // TODO Handler for creating new todo
    @PostMapping(value = "/todo")
    public ResponseEntity<TodoDTO> registerTodo(@RequestBody TodoDTO todoDto) {
//        logger.info(todoDto.toString());
        Todo todo = todoService.convertToEntity(todoDto);
        TodoDTO response = todoService.createTodo(todo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(value = "/account")
    public ResponseEntity<AccountDTO> registerAccount(@RequestBody AccountDTO accountDto) {
        Account account = accountService.convertToEntity(accountDto);
        AccountDTO response = accountService.createAccount(account);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(value = "/todo/{todo_id}")
    public ResponseEntity<TodoDTO> getTodo(@PathVariable Integer todo_id) {
        TodoDTO response = todoService.getTodoById(todo_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/todo")
    public ResponseEntity<?> getAllTodos() {
        List<TodoDTO> response = todoService.getAllTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value= "/todo/completed/{completed}")
    public ResponseEntity<List<TodoDTO>> getAllTodosByCompleted(@PathVariable("completed") boolean completed){
        List<TodoDTO> response = todoService.getAllTodosByCompleted(completed);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @PatchMapping(value = "/todo/{todo_id}")
    public ResponseEntity<TodoDTO> updateTodo(@PathVariable("todo_id") int todo_id, @RequestBody Todo todo) {
        todo.setTodo_id(todo_id);
        TodoDTO response = todoService.updateTodo(todo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(value = "/todo/account/{account_id}")
    public ResponseEntity<?> getAllTodosByAccount(@PathVariable String accountName) {
        List<TodoDTO> response = todoService.getAllTodosByAccount(accountName);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/account")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> response = accountService.getAllAccounts();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(value="/account/{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer account_id) {
        boolean isDeleted = accountService.deleteAccount(account_id);
        if (!isDeleted) {
            throw new AccountNotFoundException("Account with ID: " + account_id + " was not found. Deletion Not Completed.");
        }
        return new ResponseEntity<>("{\"message\":\"Account Successfully Deleted\"}", HttpStatus.OK);
    }


    @DeleteMapping(value="/todo/{todo_id}")
    public ResponseEntity<?> deleteTodo(@PathVariable Integer todo_id) {
        boolean isDeleted = todoService.deleteTodo(todo_id);
        if (!isDeleted) {
            throw new TodoNotFoundException("Todo with id: " + todo_id + " was not found. Deletion Not Completed.");
        }
        return new ResponseEntity<>("{\"message\":\"Todo Successfully Deleted\"}", HttpStatus.OK);
    }

}