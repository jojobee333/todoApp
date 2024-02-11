package com.revature.toDoApp.validator;

import com.revature.toDoApp.model.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class AccountValidator implements Validator {

    @Override
    public boolean supports(@NonNull Class<?> clazz){
        return Account.class.equals(clazz);
    }


    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors){
        Account account = (Account) target;

        if(account.getName() == null || account.getName().isEmpty()) {
            errors.rejectValue("accountname", "accountname.empty", "Account Name Cannot Be Empty");
        }
        if(account.getPassword() == null || account.getPassword().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password Cannot Be Empty");
        }

        if(account.getPassword().length() < 5) {
            errors.rejectValue("password", "password.too.short", "Password is Too Short");
        }
    }



}
