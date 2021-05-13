package ru.sbrf.salun_service.web.request;

import lombok.Data;

/**
 * Форма логина
 */
@Data
public class LoginRequest {

    /** Логин пользователя */
    private String username;
    /** Пароль */
    private String password;
    private String email;
}
