package org.example.tz_jwt.service;

import org.example.tz_jwt.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User registerUser(User user); // Регистрация нового пользователя
    String authenticateUser(String email, String password); // Аутентификация и возврат JWT токена
    User getUserById(Long id); // Получение пользователя по ID
    User updateUser(Long id, User user); // Обновление данных пользователя
    void deleteUser(Long id); // Удаление пользователя
}