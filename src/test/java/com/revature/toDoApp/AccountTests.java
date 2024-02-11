package com.revature.toDoApp;
import com.revature.toDoApp.controller.TodoAppController;
import com.revature.toDoApp.model.Todo;
import com.revature.toDoApp.service.AccountService;
import com.revature.toDoApp.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TodoAppController.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private TodoService todoService;
    @MockBean
    private AccountService accountService;



    @Test
    public void createAccountSuccessful() throws Exception {
        String json = "{\"name\":\"testeraccount\",\"password\": \"password\",\"isAdmin\": false }";
        Todo expectedResult = new Todo("Buy Oats", true, "testeraccount");
        when(todoService.createTodo(any())).thenReturn(expectedResult);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()); // Expecting HTTP 200

    }

    @Test
    public void createAccountNameBlank() throws Exception {
        String json = "{\"name\":\"\",\"password\": \"password\",\"isAdmin\": false }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Expecting HTTP 400
    }

    @Test
    public void createAccountPasswordBlank() throws Exception {
        String json = "{\"name\":\"testeraccount\",\"password\": \"\",\"isAdmin\": false }";

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); // Expecting HTTP 400
    }


}


// TODO Delete Account Test

// TODO Update Account Test