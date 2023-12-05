package ru.kata.spring.boot_security.rest_template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kata.spring.boot_security.rest_template.models.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    private final String usersApiUrl = "http://94.198.50.185:7081/api/users";
    public String stringBuilder = "";

    @Autowired
    public UserServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders) {
        this.restTemplate = restTemplate;
        this.httpHeaders = httpHeaders;
        httpHeaders.add("Cookie", String.join("", getAllUsers()));
    }

    // Метод для получения списка всех пользователей
    public List<String> getAllUsers() {
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(usersApiUrl, HttpMethod.GET, requestEntity, String.class);
        return response.getHeaders().get("Set-Cookie");
    }

    // Метод для добавления нового пользователя
    public void saveUser(User userJson) {
        HttpEntity<User> requestEntity = new HttpEntity<>(userJson, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(usersApiUrl, HttpMethod.POST, requestEntity, String.class);
        stringBuilder = stringBuilder + response.getBody();
    }

    // Метод для обновления информации о пользователе
    public void updateUser(User userJson) {
        HttpEntity<User> requestEntity = new HttpEntity<>(userJson, httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(usersApiUrl, HttpMethod.PUT, requestEntity, String.class);
        stringBuilder = stringBuilder + response.getBody();

    }

    // Метод для удаления пользователя
    public void deleteUser(Long userId) {
        HttpEntity<User> requestEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(usersApiUrl + "/" + userId, HttpMethod.DELETE, requestEntity, String.class);
        stringBuilder = stringBuilder + response.getBody();
    }
}

