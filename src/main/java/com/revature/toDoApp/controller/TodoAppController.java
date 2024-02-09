package com.revature.toDoApp.controller;
import com.revature.toDoApp.exception.InvalidAccountException;
import com.revature.toDoApp.exception.InvalidTodoException;
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
    public TodoAppController(TodoService todoService) {
        this.todoService = todoService;

    }

    private static final Logger logger = LoggerFactory.getLogger(TodoAppController.class);

    // TODO Handler for creating new todo
    @PostMapping(value = "/todo")
    public ResponseEntity<Todo> registerTodo(@RequestBody Todo todo) {
        Optional<Todo> response = todoService.createTodo(todo);
        if (response.isEmpty()) {
            throw new InvalidTodoException("Error Creating Todo");

        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);

    }

    @GetMapping(value = "/todo")
    public ResponseEntity<?> getAllTodos() {
        Optional<List<Todo>> response = todoService.getAllTodos();
        if (response.isEmpty()) {
            return new ResponseEntity<>("No entries found", HttpStatus.OK);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PatchMapping(value = "/todo")
    public ResponseEntity<Todo> updateTodo(@RequestBody Todo todo) {
        Todo response = todoService.updateTodo(todo);
        return new ResponseEntity<Todo>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/account/{account_name}/todo")
    public ResponseEntity<?> getAllTodosByAccount(@PathVariable String account_name) {
        Optional<List<Todo>> response = todoService.getAllTodosByAccount(account_name);
        if (response.isEmpty()) {
            return new ResponseEntity<>("No entries found", HttpStatus.OK);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }



    @GetMapping(value = "/account")
    public ResponseEntity<?> getAllAccounts() {
        Optional<List<Account>> response = accountService.getAllAccounts();
        if (response.isEmpty()) {
            return new ResponseEntity<>("No entries found", HttpStatus.OK);
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PostMapping(value = "/account")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        Optional<Account> response = accountService.createAccount(account);
        if (response.isEmpty()) {
            throw new InvalidAccountException("Error Creating Account");
        }
        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }




    // TODO Handler for deleting a todo

    // TODO Handler for updating a todo

}