package edu.school21.cinema.services;

import edu.school21.cinema.models.User;

import java.util.Map;

public interface UserService {
    User getUser(String email);

    boolean saveUser(User user);
}