package ru.kata.spring.boot_security.demo.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import java.security.InvalidParameterException;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Transactional
    public User showOneUser(int id) {
        Optional<User> findOneUserById = userRepository.findById(id);
        return findOneUserById.orElse(null);
    }

    @Transactional
    public boolean save(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());

        if (userFromDB != null) {
            System.out.println("Пользователь с таким именем уже есть в базе");
            return false;
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }
    }


    @Transactional
    public boolean update(User user) throws InvalidParameterException {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && existingUser.getId() != (user.getId())) {
            return false;
        }
        if (user.getPassword().isEmpty()) {
            user.setPassword(userRepository.findById(user.getId()).get().getPassword());
        } else {
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        }
        userRepository.save(user);
        return true;
    }


    @Transactional
    public boolean delete(int id) {
        if (userRepository.findById(id).isEmpty()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

}