package com.revature.toDoApp.service;


import com.revature.toDoApp.exception.InvalidTodoException;
import com.revature.toDoApp.exception.AccountNotFoundException;
import com.revature.toDoApp.exception.TodoNotFoundException;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.repository.TodoRepository;
import com.revature.toDoApp.validator.TodoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import java.util.List;

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

    private void validateTodo(Todo todo){
        Errors errors = new BeanPropertyBindingResult(todo, "todo");
        todoValidator.validate(todo, errors);
        if(errors.hasErrors()){
            throw new InvalidTodoException("Todo is Invalid.", errors);
        }

    }

    public Todo createTodo(Todo todo) {
        validateTodo(todo);

        // Check if the account exists
        accountRepository.findByName(todo.getAccount_name())
                .orElseThrow(() -> new InvalidTodoException("No Valid Account Associated with Todo."));

        return todoRepository.save(todo);
    }

    public List<Todo> getAllTodosByAccount(String account_name) {
        return todoRepository.findByAccountName(account_name)
                .orElseThrow(() -> new AccountNotFoundException("Account Not Found."));
    }



    public List<Todo> getAllTodos(){
            return todoRepository.findAll();
    }


    public Todo getTodoById(int todo_id){
        return todoRepository.findById(todo_id)
                .orElseThrow(() -> new TodoNotFoundException("Todo Not Found"));
    }


    public boolean deleteTodo(Integer todo_id){
      return todoRepository.findById(todo_id)
              .map(todo -> {
                  todoRepository.deleteById(todo_id);
                  return true;
                      })
              .orElse(false);
    }

    public Todo updateTodo(Todo todoUpdate) {
        validateTodo(todoUpdate);
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
