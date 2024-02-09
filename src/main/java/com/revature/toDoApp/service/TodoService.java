package com.revature.toDoApp.service;


import com.revature.toDoApp.exception.InvalidTodoException;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.exception.TodoNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.repository.TodoRepository;
import com.revature.toDoApp.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {


    // validate account exists prior to submitting todo


    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TodoValidator todoValidator;


    // new to-do


    public Optional<Todo> createTodo(Todo todo) {

        Errors errors = new BeanPropertyBindingResult(todo, "todo");
        todoValidator.validate(todo, errors);
        if(errors.hasErrors()){
            throw new InvalidTodoException("Todo is Invalid.", errors);
        }


        // check the account exists.
        Optional<Account> existingAccount = accountRepository.findByName(todo.getAccount_name());
        if (existingAccount.isEmpty()) {
            throw new InvalidTodoException("No Valid Account Associated with Todo.");
        }
        return Optional.of(todoRepository.save(todo));
    }

    public Optional<List<Todo>> getAllTodosByAccount(String account_name) {
        return Optional.ofNullable(todoRepository.findByAccountName(account_name))
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found."));
    }



    public Optional<List<Todo>> getAllTodos(){
            return Optional.of(todoRepository.findAll());
    }


    public Optional<Todo> getTodoById(int todoId){
            Optional<Todo> todo = todoRepository.findById(todoId);
            if(todo.isPresent()){
                return todo;
            }
            throw new TodoNotFoundException("Todo Not Found");
    }

    public Optional<Todo> getTodoByName(String text){
        Optional<Todo> todo = todoRepository.findByText(text);
        if(todo.isPresent()){
            return todo;
        }
        throw new TodoNotFoundException("Todo Not Found");
    }


    public Integer deleteTodo(int todoId){
        int rowsChanged = 0;
        try{
            Optional<Todo> todoToDelete = todoRepository.findById(todoId);
            if(todoToDelete.isPresent()){
                todoRepository.deleteById(todoId);
                rowsChanged += 1;
            }
        }
        catch(Exception ignored){

        }
        return rowsChanged;

    }

    public Todo updateTodo(Todo todoUpdate) {
        Errors errors = new BeanPropertyBindingResult(todoUpdate, "todo");
        todoValidator.validate(todoUpdate, errors);

        if (errors.hasErrors()) {
            throw new InvalidTodoException("Todo is Invalid.", errors);
        }

        Todo existingTodo = todoRepository.findById(todoUpdate.getTodo_id())
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + todoUpdate.getTodo_id()));

        if (todoUpdate.getText() != null) {
            existingTodo.setText(todoUpdate.getText());
        }
        if (todoUpdate.isCompleted() != null) {
            existingTodo.setCompleted(todoUpdate.isCompleted());
        }
        if (todoUpdate.getAccount_name() != null) {
            existingTodo.setAccount_name(todoUpdate.getAccount_name());
        }

        return todoRepository.save(existingTodo);
    }



}
