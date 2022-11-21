package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserValidatorImpl implements UserValidator{

    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errors = new HashMap<>();

        checkName(user.getName(), errors);
        checkSurname(user.getSurname(), errors);
        checkPhone(user.getPhone(), errors);
        checkEmail(user.getEmail(), errors);
        checkPassword(user.getPassword(), errors);

        return errors;
    }

    private void checkName(@Nullable String name, Map<String, String> errors){
        if (!StringUtils.isEmpty(name)){
            if (name.length() > 30){
                errors.put("nameError", "Name is too long!");
            }
        }
    }

    private void checkSurname(@Nullable String name, Map<String, String> errors){
        if (!StringUtils.isEmpty(name)){
            if (name.length() > 30){
                errors.put("surnameError", "Surname is too long!");
            }
        }

    }

    private void checkPhone(@Nullable String phone, Map<String, String> errors){
        if (!StringUtils.isEmpty(phone)){
            String regex = "^((8|\\+7)[\\- ]?)?(\\(?\\d{3}\\)?[\\- ]?)?[\\d\\- ]{7,10}$";
            if (!phone.matches(regex)){
                errors.put("phoneError", "Phone number is bad!");
            }
        }
    }

    private void checkEmail(@Nullable String email, Map<String, String> errors){
        if (StringUtils.isEmpty(email)){
            errors.put("emailError", "Email must not be empty!");
            return;
        }
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        if (!email.matches(regexPattern)){
            errors.put("emailError", "Email address is bad!");
        }
    }

    private void checkPassword(@Nullable String password, Map<String, String> errors){
        if (StringUtils.isEmpty(password)){
            errors.put("emailError", "Email must not be empty!");
            return;
        }
        if (password.length() < 5){
            errors.put("passwordError", "Email address is bad!");
        }
    }

}
