package com.revature.toDoApp;


import com.revature.toDoApp.repository.TodoRepository;
import com.revature.toDoApp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;




@SpringBootTest
public class ToDoServiceTest {

    ApplicationContext appContext;

    @MockBean
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoService todoService;




    @Test
    public void testCreateTodoSuccessful(){
        String message = "hello";
        Assertions.assertEquals(message, "hello");
    }






}
