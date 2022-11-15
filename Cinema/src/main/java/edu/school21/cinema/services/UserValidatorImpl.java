package edu.school21.cinema.services;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@PropertySource("classpath:application.properties")
public class UserValidatorImpl implements UserValidator{
    @Value("${recaptcha.secret}") //секретный код получили при создании капчи
    public String captchaSecret;

    @Override
    public Map<String, String> validate(User user) {
        Map<String, String> errors = new HashMap<>();

        return null;
    }

}
