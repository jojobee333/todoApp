package com.revature.toDoApp;

import com.revature.toDoApp.dto.TodoDTO;
import com.revature.toDoApp.exception.InvalidTodoException;
import com.revature.toDoApp.exception.TodoNotFoundException;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.repository.TodoRepository;
import com.revature.toDoApp.service.TodoService;
import com.revature.toDoApp.validator.TodoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.Errors;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TodoValidator todoValidator;

    @InjectMocks
    private TodoService todoService;

    private Todo todo;
    private TodoDTO todoDTO;
    private Account account;

    @BeforeEach
    void setUp() {
        account = new Account();
        account.setAccountId(1);

        todo = new Todo();
        todo.setTodo_id(1);
        todo.setText("Test Todo");
        todo.setCompleted(true);
        todo.setAccount(account);

        todoDTO = new TodoDTO();
        todoDTO.setTodoId(2);
        todoDTO.setText("Test Todo");
        todoDTO.setCompleted(false);
        todoDTO.setAccountId(1);
    }

    @Test
    void createTodo_Success() {
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        TodoDTO result = todoService.createTodo(todo);

        assertNotNull(result);
        assertEquals(todo.getTodo_id(), result.getTodoId());
        verify(todoValidator).validate(any(Todo.class), any(Errors.class));
    }

    @Test
    void createTodo_InvalidTodoException() {
        doAnswer(invocation -> {
            Errors errors = invocation.getArgument(1);
            errors.reject("error");
            return null;
        }).when(todoValidator).validate(any(Todo.class), any(Errors.class));

        assertThrows(InvalidTodoException.class, () -> todoService.createTodo(todo));
    }

    @Test
    void getAllTodosByAccount_Success() {
        when(todoRepository.findByAccountId(account.getAccountId())).thenReturn(Arrays.asList(todo));

        List<TodoDTO> results = todoService.getAllTodosByAccount(account.getAccountId());

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(todo.getTodo_id(), results.get(0).getTodoId());
    }

    @Test
    void getTodoById_Success() {
        when(todoRepository.findById(todo.getTodo_id())).thenReturn(Optional.of(todo));

        TodoDTO result = todoService.getTodoById(todo.getTodo_id());

        assertNotNull(result);
        assertEquals(todo.getTodo_id(), result.getTodoId());
    }

    @Test
    void getTodoById_NotFound() {
        when(todoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById(99));
    }

    // Continuing from the previous test class

    @Test
    void deleteTodo_Success() {
        when(todoRepository.findById(todo.getTodo_id())).thenReturn(Optional.of(todo));
        doNothing().when(todoRepository).deleteById(todo.getTodo_id());

        boolean result = todoService.deleteTodo(todo.getTodo_id());

        assertTrue(result);
        verify(todoRepository).deleteById(todo.getTodo_id());
    }

    @Test
    void deleteTodo_NotFound() {
        when(todoRepository.findById(anyInt())).thenReturn(Optional.empty());

        boolean result = todoService.deleteTodo(99);

        assertFalse(result);
    }

    @Test
    void updateTodo_Success() {
        Todo updatedTodo = new Todo();
        updatedTodo.setTodo_id(todo.getTodo_id());
        updatedTodo.setText("Updated Todo");
        updatedTodo.setCompleted(true);

        when(todoRepository.findById(todo.getTodo_id())).thenReturn(Optional.of(todo));
        when(todoRepository.save(any(Todo.class))).thenReturn(updatedTodo);

        TodoDTO result = todoService.updateTodo(updatedTodo);

        assertNotNull(result);
        assertEquals(updatedTodo.getText(), result.getText());
        assertEquals(updatedTodo.getCompleted(), result.getCompleted());
    }

    @Test
    void updateTodo_NotFound() {
        when(todoRepository.findById(anyInt())).thenReturn(Optional.empty());

        assertThrows(TodoNotFoundException.class, () -> todoService.updateTodo(todo));
    }

    @Test
    void getAllTodos_Success() {
        when(todoRepository.findAll()).thenReturn(Arrays.asList(todo));

        List<TodoDTO> results = todoService.getAllTodos();

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertEquals(todo.getTodo_id(), results.get(0).getTodoId());
    }

    @Test
    void getAllTodosByCompleted_Success() {
        when(todoRepository.findByCompleted(true)).thenReturn(Arrays.asList(todo));

        List<TodoDTO> results = todoService.getAllTodosByCompleted(true);

        assertFalse(results.isEmpty());
        assertEquals(1, results.size());
        assertTrue(results.get(0).getCompleted());
    }

// Additional tests can be added for other scenarios and edge cases

}
