package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Map;

public interface UserValidator {

    Map<String, String> validate(User user);
}
