package edu.school21.cinema.repositories;

import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class UserRepository {

    private static Long id;

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

    public void add(User user) {
        if (id == null || id == 0){
            id = 2L;
        }
        jdbcTemplate.update("insert into usr (id, name, surname, phone, email, password, active) values (?, ?, ?, ?, ?, ?, ?)",
        ++id ,user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.isActive());

    }
}
