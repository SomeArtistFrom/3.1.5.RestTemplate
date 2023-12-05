package ru.kata.spring.boot_security.rest_template.services;

import ru.kata.spring.boot_security.rest_template.models.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);


    void updateUser(User user);

    void deleteUser(Long id);

    List<String> getAllUsers();

}