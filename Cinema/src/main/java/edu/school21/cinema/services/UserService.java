package edu.school21.cinema.services;

import edu.school21.cinema.models.SessionData;
import edu.school21.cinema.models.User;

import java.util.Map;

public interface UserService {
    User getUser(String email);

    User getUserById(Long id);

    boolean saveUser(User user);

    void saveSessionData(SessionData data);
}
