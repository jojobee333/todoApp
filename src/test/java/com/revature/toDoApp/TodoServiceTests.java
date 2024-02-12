package com.revature.toDoApp;

import com.revature.toDoApp.exception.*;
import com.revature.toDoApp.model.Account;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.repository.AccountRepository;
import com.revature.toDoApp.repository.TodoRepository;
import com.revature.toDoApp.service.TodoService;
import com.revature.toDoApp.validator.TodoValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.openMocks;

class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TodoValidator todoValidator;

    @InjectMocks
    private TodoService todoService;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }


    // Test creating a valid Todo
    @Test
    void createTodo_ValidTodo_ReturnsTodo() {
        // Setup: Create a Todo object and mock the behavior of dependencies
        Todo todo = new Todo("Test Todo", false, "testeraccount");
        when(accountRepository.findByName("testeraccount")).thenReturn(Optional.of(new Account()));
        when(todoRepository.save(any(Todo.class))).thenReturn(todo);

        // Execution: Call the method to be tested
        Todo result = todoService.createTodo(todo);

        // Assertions: Check if the result is as expected
        assertNotNull(result);
        verify(todoRepository).save(todo); // Verify interaction with mock
    }

    // Test creating an invalid Todo
    @Test
    void createTodo_InvalidTodo_ThrowsException() {
        // Setup: Create an invalid Todo object and mock the validator behavior
        Todo todo = new Todo("", false, "testeraccount");
        Errors errors = new BeanPropertyBindingResult(todo, "todo");
        doAnswer(invocation -> {
            errors.reject("error");
            return null;
        }).when(todoValidator).validate(eq(todo), any(Errors.class));

        // Execution & Assertions: Expect an exception to be thrown
        assertThrows(InvalidTodoException.class, () -> todoService.createTodo(todo));
    }

    // Test getting all Todos by account name when account exists
    @Test
    void getAllTodosByAccount_AccountExists_ReturnsTodos() {
        // Setup: Mock the repository to return an empty list of Todos
        when(todoRepository.findByAccountName("testeraccount")).thenReturn(Optional.of(Collections.emptyList()));

        // Execution: Call the method to be tested
        List<Todo> result = todoService.getAllTodosByAccount("testeraccount");

        // Assertions: Check if the result is as expected
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    // Test getting all Todos by account name when account does not exist
    @Test
    void getAllTodosByAccount_AccountDoesNotExist_ThrowsException() {
        // Setup: Mock the repository to return an empty Optional
        when(todoRepository.findByAccountName("nonexistent")).thenReturn(Optional.empty());

        // Execution & Assertions: Expect an exception to be thrown
        assertThrows(AccountNotFoundException.class, () -> todoService.getAllTodosByAccount("nonexistent"));
    }

    // Test getting a Todo by ID when the Todo exists
    @Test
    void getTodoById_TodoExists_ReturnsTodo() {
        // Setup: Create a Todo object and mock the repository behavior
        Todo todo = new Todo("Test Todo", false, "testeraccount");
        when(todoRepository.findById(1)).thenReturn(Optional.of(todo));

        // Execution: Call the method to be tested
        Todo result = todoService.getTodoById(1);

        // Assertions: Check if the result is as expected
        assertNotNull(result);
        assertEquals("Test Todo", result.getText());
    }

    // Test getting a Todo by ID when the Todo does not exist
    @Test
    void getTodoById_TodoDoesNotExist_ThrowsException() {
        // Setup: Mock the repository to return an empty Optional
        when(todoRepository.findById(99)).thenReturn(Optional.empty());

        // Execution & Assertions: Expect an exception to be thrown
        assertThrows(TodoNotFoundException.class, () -> todoService.getTodoById(99));
    }

    // Test deleting a Todo when the Todo exists
    @Test
    void deleteTodo_Valid_ReturnsTrue(){
        // Setup: Create a Todo ID and mock the repository behavior
        Integer todo_id = 1;
        Todo todo = new Todo("Todo to Delete", false, "testeraccount");
        when(todoRepository.findById(todo_id)).thenReturn(Optional.of(todo));

        // Execution: Call the method to be tested
        boolean result = todoService.deleteTodo(todo_id);

        // Assertions: Check if the result is as expected
        assertTrue(result);
        verify(todoRepository).deleteById(todo_id); // Verify interaction with mock
    }

    // Test deleting a Todo when the Todo does not exist
    @Test
    void deleteTodo_TodoDoesNotExist_ReturnsFalse(){
        // Setup: Create a Todo ID and mock the repository to return an empty Optional
        Integer todo_id = 2;
        when(todoRepository.findById(todo_id)).thenReturn(Optional.empty());

        // Execution: Call the method to be tested
        boolean result = todoService.deleteTodo(todo_id);

        // Assertions: Check if the result is as expected
        assertFalse(result);
        verify(todoRepository, never()).deleteById(anyInt()); // Verify no interaction with mock
    }


    @Test
    void updateTodo_ValidTodo_ReturnsUpdatedTodo(){
        // Arrange
        int todo_id = 3;
        Todo existingTodo = new Todo("Original Text", false, "originalAccount" );
        Todo todoUpdate = new Todo("Updated Text", true, "updatedAccount");

        todoUpdate.setTodo_id(todo_id);
        existingTodo.setTodo_id(todo_id);

        when(todoRepository.findById(todo_id)).thenReturn(Optional.of(existingTodo));
        when(todoRepository.save(any(Todo.class))).thenReturn(todoUpdate);

        // Act
        Todo updatedTodo = todoService.updateTodo(todoUpdate);

        // Assertions: Check Each Field of result.  Check if result is empty.
        assertEquals(todoUpdate.getText(), updatedTodo.getText());
        assertEquals(todoUpdate.isCompleted(), updatedTodo.isCompleted());
        assertEquals(todoUpdate.getAccount_name(), updatedTodo.getAccount_name());
        verify(todoRepository).save(existingTodo);

    }

    @Test
    void updateTodo_TodoNotFound_ThrowsException() {
        // Arrange
        int todoId = 4;
        Todo todoUpdate = new Todo("Updated Text", true, "updatedAccount");
        todoUpdate.setTodo_id(todoId);

        when(todoRepository.findById(todoId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TodoNotFoundException.class, () -> todoService.updateTodo(todoUpdate));
    }


}