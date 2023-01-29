package edu.school21.cinema.services;

import edu.school21.cinema.models.ImageInfo;
import edu.school21.cinema.models.SessionData;
import edu.school21.cinema.models.User;
import edu.school21.cinema.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User getUser(String email) {

       User user = repository.findUserByEmail(email);
        if (user != null){
            user.setImageInfos(repository.getImageInfoList(user.getId()));
            user.setSessionDataList(repository.getSessionDataList(user.getId()));
        }
        return user;
    }

    @Override
    public User getUserById(Long id) {
        return repository.findUserById(id);
    }

    @Override
    public boolean saveUser(User user) {
        if (repository.findUserByEmail(user.getEmail()) != null){
            return false;
        }
        user.setActive(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.add(user);
        return true;
    }

    @Override
    public void saveSessionData(SessionData data) {
        repository.add(data);
    }

    @Override
    public void addImageInfo(ImageInfo imageInfo){
        repository.add(imageInfo);
        repository.update(imageInfo.getName(), imageInfo.getUsr_id());
    }

}
