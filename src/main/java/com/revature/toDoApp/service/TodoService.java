package com.revature.toDoApp.service;

import com.revature.toDoApp.dto.TodoDTO;
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
import java.util.stream.Collectors;

@Service
public class TodoService {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TodoValidator todoValidator;

    public TodoDTO convertToDto(Todo todo) {
        TodoDTO todoDto = new TodoDTO();
        todoDto.setTodoId(todo.getTodo_id());
        todoDto.setText(todo.getText());
        todoDto.setCompleted(todo.getCompleted());
        if (todo.getAccount() != null) {
            todoDto.setAccountId(todo.getAccount().getAccountId());
        }
        // Add other fields as necessary
        return todoDto;
    }

    public Todo convertToEntity(TodoDTO todoDto) {
        Todo todo = new Todo();
        todo.setTodo_id(todoDto.getTodoId());
        todo.setText(todoDto.getText());
        todo.setCompleted(todoDto.getCompleted());

            Account account = accountRepository.findById(todoDto.getAccountId())
                    .orElseThrow(() -> new AccountNotFoundException("Account Not Found with ID: " + todoDto.getAccountId()));
            todo.setAccount(account);

        // Add other fields as necessary
        return todo;
    }

    private void validateTodo(Todo todo) {
        Errors errors = new BeanPropertyBindingResult(todo, "todo");
        todoValidator.validate(todo, errors);
        if (errors.hasErrors()) {
            throw new InvalidTodoException("Todo is Invalid.", errors);
        }
    }

    public TodoDTO createTodo(Todo todo) {
        validateTodo(todo);
        Todo savedTodo = todoRepository.save(todo);
        return convertToDto(savedTodo);
    }

    public List<TodoDTO> getAllTodosByAccount(int account_id) {
        return todoRepository.findByAccountId(account_id).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TodoDTO> getAllTodos() {
        return todoRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<TodoDTO> getAllTodosByCompleted(boolean completed) {
        return todoRepository.findByCompleted(completed).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TodoDTO getTodoById(int todo_id) {
        Todo todo = todoRepository.findById(todo_id)
                .orElseThrow(() -> new TodoNotFoundException("Todo Not Found"));
        return convertToDto(todo);
    }

    public boolean deleteTodo(Integer todo_id) {
        return todoRepository.findById(todo_id)
                .map(todo -> {
                    todoRepository.deleteById(todo_id);
                    return true;
                })
                .orElse(false);
    }

    public TodoDTO updateTodo(Todo todoUpdate) {
        validateTodo(todoUpdate);
        Todo existingTodo = todoRepository.findById(todoUpdate.getTodo_id())
                .orElseThrow(() -> new TodoNotFoundException("Todo not found with id: " + todoUpdate.getTodo_id()));

        if (todoUpdate.getText() != null) {
            existingTodo.setText(todoUpdate.getText());
        }
        if (todoUpdate.getCompleted() != null) {
            existingTodo.setCompleted(todoUpdate.getCompleted());
        }

        Todo updatedTodo = todoRepository.save(existingTodo);
        return convertToDto(updatedTodo);
    }
}
