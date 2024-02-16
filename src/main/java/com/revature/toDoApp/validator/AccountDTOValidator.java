package com.revature.toDoApp.validator;

import com.revature.toDoApp.dto.AccountDTO;
import com.revature.toDoApp.model.Account;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class AccountDTOValidator implements Validator {


    @Override
    public boolean supports(@NonNull Class<?> clazz) {
        return AccountDTO.class.equals(clazz);
    }

    @Override
    public void validate(@NonNull Object target, @NonNull Errors errors) {
        AccountDTO account = (AccountDTO) target;

        if (account.getAccountName() == null || account.getAccountName().isEmpty()) {
            errors.rejectValue("account_name", "account_name.empty", "Account Name Cannot Be Empty");

        }

        if (account.getPassword() == null || account.getPassword().isEmpty()) {
            errors.rejectValue("password", "password.empty", "Password Cannot Be Empty");

        } else if (account.getPassword().length() < 5) {
            errors.rejectValue("password", "password.too.short", "Password is Too Short");

        }

        // Additional validation rules can be added here
    }
}
