package com.revature.toDoApp.controller;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.exception.InvalidAccountException;
import com.revature.toDoApp.exception.InvalidTodoException;
import com.revature.toDoApp.exception.TodoNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.service.AccountService;
import com.revature.toDoApp.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class TodoAppController {


    // TODO Dependency Injection

    @Autowired
    private TodoService todoService;
    @Autowired
    private AccountService accountService;


    @Autowired
    public TodoAppController(TodoService todoService, AccountService accountService) {
        this.todoService = todoService;
        this.accountService = accountService;

    }

    private static final Logger logger = LoggerFactory.getLogger(TodoAppController.class);

    // TODO Handler for creating new todo
    @PostMapping(value = "/todo")
    public ResponseEntity<Todo> registerTodo(@RequestBody Todo todo) {
        Todo response = todoService.createTodo(todo);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @GetMapping(value = "/todo/{todo_id}")
    public ResponseEntity<Todo> getTodo(@PathVariable Integer todo_id) {
        Todo response = todoService.getTodoById(todo_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/todo")
    public ResponseEntity<?> getAllTodos() {
        List<Todo> response = todoService.getAllTodos();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(value = "/todo/{todo_id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("todo_id") int todo_id, @RequestBody Todo todo) {
        todo.setTodo_id(todo_id);
        Todo response = todoService.updateTodo(todo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(value = "/account/{account_name}/todo")
    public ResponseEntity<?> getAllTodosByAccount(@PathVariable String account_name) {
        List<Todo> response = todoService.getAllTodosByAccount(account_name);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping(value = "/account")
    public ResponseEntity<?> getAllAccounts() {
        List<Account> response = accountService.getAllAccounts();
        return new ResponseEntity<>(response, HttpStatus.OK);
        }


    @PostMapping(value = "/account")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Account response = accountService.createAccount(account);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping(value="/account/{account_id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Integer account_id) {
        boolean isDeleted = accountService.deleteAccount(account_id);
        if (!isDeleted) {
            throw new AccountNotFoundException("Account with name: " + account_id + " was not found. Deletion Not Completed.");
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