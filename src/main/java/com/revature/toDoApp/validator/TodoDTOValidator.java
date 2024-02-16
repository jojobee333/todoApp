package com.revature.toDoApp.validator;


import com.revature.toDoApp.dto.TodoDTO;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class TodoDTOValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz){
        return TodoDTO.class.equals(clazz);
    }


    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors){
        TodoDTO todo = (TodoDTO) target;

        if(todo.getText() == null || todo.getText().isEmpty()) {
            errors.rejectValue("text", "text.empty", "Todo Text Cannot Be Empty");
        }
        if(todo.getText().length() > 100) {
            errors.rejectValue("text", "text.too.long", "Todo Text Exceed Max Length");
        }
    }



}
