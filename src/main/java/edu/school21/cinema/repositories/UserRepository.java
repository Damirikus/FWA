package edu.school21.cinema.repositories;

import edu.school21.cinema.models.ImageInfo;
import edu.school21.cinema.models.SessionData;
import edu.school21.cinema.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User findUserByEmail(String email){
        return jdbcTemplate.query("select * from usr where email=?", new UserMapper(), email)
                .stream().findAny().orElse(null);
    }

    public List<ImageInfo> getImageInfoList(Long usr_id){
        String sql = "select * from images where usr_id=?";
        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(ImageInfo.class), usr_id);
    }

    public List<SessionData> getSessionDataList(Long usr_id){
        String sql = "select * from session_data where usr_id=?";
        return jdbcTemplate.query(
                sql, new BeanPropertyRowMapper<>(SessionData.class), usr_id);
    }

    public User findUserById(Long id){
        return jdbcTemplate.query("select * from usr where usr_id=?", new UserMapper(), id)
                .stream().findAny().orElse(null);
    }

    public void add(User user) {
        jdbcTemplate.update("insert into usr (name, surname, phone, email, password, active) values (?, ?, ?, ?, ?, ?)",
        user.getName(), user.getSurname(), user.getPhone(), user.getEmail(), user.getPassword(), user.isActive());
    }

    public void add(SessionData data){
        jdbcTemplate.update("insert into session_data (usr_id, date, ip) values (?, ?, ?)",
                data.getUsr_id(), data.getDate(), data.getIp());
    }

    public void add(ImageInfo imageInfo){
        jdbcTemplate.update("insert into images (usr_id, name, mime, size) values (?, ?, ?, ?)",
                imageInfo.getUsr_id(), imageInfo.getName(), imageInfo.getMime(), imageInfo.getSize());
    }

    public void update(String filename, Long id){
        jdbcTemplate.update("update usr set filename=? where usr_id=?", filename, id);
    }
}
