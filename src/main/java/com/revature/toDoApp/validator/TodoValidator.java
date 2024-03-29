package com.revature.toDoApp.validator;


import com.revature.toDoApp.model.Todo;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class TodoValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz){
        return Todo.class.equals(clazz);
    }


    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors){
        Todo todo = (Todo) target;

        if(todo.getText() == null || todo.getText().isEmpty()) {
            errors.rejectValue("text", "text.empty", "Todo Text Cannot Be Empty");
        }
        if(todo.getText().length() > 100) {
            errors.rejectValue("text", "text.too.long", "Todo Text Exceed Max Length");
        }
    }



}
