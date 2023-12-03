package ru.kata.spring.boot_security.demo.services;

import ru.kata.spring.boot_security.demo.models.User;
import java.util.List;

public interface UserService {

    boolean saveUser(User user);

    User getUserById(Long id);

    boolean updateUser(User user);

    boolean deleteUser(Long id);

    List<User> getAllUsers();

    User getUserByUsername(String name);


    User getAuthenticatedUser();
}