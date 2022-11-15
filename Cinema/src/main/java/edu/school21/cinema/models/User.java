package edu.school21.cinema.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;
import java.util.Set;

@Data
@AllArgsConstructor
public class User {

    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private String activationCode;
    private boolean active;
    private Set<Role> roles;

    public User() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getEmail().equals(user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
