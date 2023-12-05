package ru.kata.spring.boot_security.rest_template;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.rest_template.models.User;
import ru.kata.spring.boot_security.rest_template.services.UserService;
import ru.kata.spring.boot_security.rest_template.services.UserServiceImpl;

import java.io.ObjectInputFilter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String args[]) {
        ApplicationContext ctx = SpringApplication.run(Application.class, args);
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ObjectInputFilter.Config.class);
        UserServiceImpl userServiceImpl = ctx.getBean(UserServiceImpl.class);

        //Получить всех
        List<String> all = userServiceImpl.getAllUsers();

        // Добавление нового пользователя
        User newUser = new User(3L, "James", "Brown", (byte) 30);
        userServiceImpl.saveUser(newUser);

        //Изменение James
        User userToUpdate = new User(3L, "Thomas", "Shelby", (byte) 38);
        userServiceImpl.updateUser(userToUpdate);

        // Удаление пользователя
        userServiceImpl.deleteUser(3L);

        System.out.println(userServiceImpl.stringBuilder);
    }
}

