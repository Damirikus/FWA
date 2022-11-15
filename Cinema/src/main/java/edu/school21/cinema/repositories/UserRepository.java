package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByEmail(String email){

        // todo переделать запрос, чтобы доставать и роли
        return jdbcTemplate.query("select * from usr where email=?", new UserMapper(), email)
                .stream().findAny().orElse(null);
    }

    public User findUserById(Long id){
        return jdbcTemplate.query("select * from usr where id=?", new UserMapper(), id)
                .stream().findAny().orElse(null);
    }
}
