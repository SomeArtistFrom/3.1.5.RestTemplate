package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.SessionIdInterceptor;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {

	public static void main(String args[]) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
		UserService userService = ctx.getBean(UserService.class);
		UserServiceImpl userServiceImpl = ctx.getBean(UserServiceImpl.class);
		SessionIdInterceptor sessionIdInterceptor = ctx.getBean(SessionIdInterceptor.class);

		RestTemplate restTemplate = new RestTemplate();


		//Получить всех
//		User all = restTemplate.getForObject("http://94.198.50.185:7081/api/users", User.class);

		List<User> all= userService.getAllUsers();
		sessionIdInterceptor.setSessionId("http://94.198.50.185:7081");
		restTemplate.setInterceptors(Collections.singletonList(sessionIdInterceptor));

		// Добавление нового пользователя
		User newUser = new User("James", "Brown", (byte)30);
		User createdUser = restTemplate.postForObject("http://94.198.50.185:7081/api/users", newUser, User.class);
		System.out.println("Новый пользователь добавлен. ID: " + createdUser.getId());

		//Изменение James
		User userToUpdate = createdUser;
		userToUpdate.setName("Thomas");
		userToUpdate.setLastName("Shelby");
		restTemplate.put("http://94.198.50.185:7081/api/users/" + userToUpdate.getId(), userToUpdate);
		System.out.println("Пользователь с ID " + userToUpdate.getId() + " успешно обновлён.");

		// Удаление пользователя
		restTemplate.delete("http://94.198.50.185:7081/api/users/" + userToUpdate.getId());
		System.out.println("Пользователь с ID " + userToUpdate.getId() + " успешно удалён.");
	}
}

