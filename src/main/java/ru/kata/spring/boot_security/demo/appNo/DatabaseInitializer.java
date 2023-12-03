package ru.kata.spring.boot_security.demo.appNo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class DatabaseInitializer {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DatabaseInitializer(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void addData(){

        Role userR = new Role(1L, "ROLE_USER");
        Role adminR = new Role(2L, "ROLE_ADMIN");

        Set<Role> userRoles = new HashSet<>();
        Set<Role> adminRoles = new HashSet<>();

        roleRepository.save(userR);
        roleRepository.save(adminR);

        adminRoles.add(userR);
        adminRoles.add(adminR);

        userRoles.add(userR);

        User user = new User("user", 28, "$2a$10$zAh7UkijN9qslJD8QAxvjeRxC7AX6PS4UPcpSVrXGGnsmr.A9yg/K", "UserProf", userRoles);
        User admin = new User("admin", 89, "$2a$10$4Debu.ACttax8i3/dOPHaehqwllqv53nE/AYuONnL74oSdg3s012i", "AdminProf", adminRoles);
        User irina = new User("irina", 29, "$2a$10$zAh7UkijN9qslJD8QAxvjeRxC7AX6PS4UPcpSVrXGGnsmr.A9yg/K", "Artist", userRoles);
        User test = new User("test", 100, "$2a$10$zAh7UkijN9qslJD8QAxvjeRxC7AX6PS4UPcpSVrXGGnsmr.A9yg/K", "Test", userRoles);

        userRepository.save(user);
        userRepository.save(admin);
        userRepository.save(irina);
        userRepository.save(test);
    }
}