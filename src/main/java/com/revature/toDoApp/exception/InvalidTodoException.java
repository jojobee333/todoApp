package com.revature.toDoApp.exception;



import org.springframework.validation.Errors;

public class InvalidTodoException extends RuntimeException{

    private Errors errors;

    public InvalidTodoException(String msg, Errors errors){
        super(msg);
        this.errors = errors;
    }

    public InvalidTodoException(String msg){
        super(msg);
    }

    public Errors getErrors(){
        return errors;
    }


}