package ru.sbrf.salun_service.web.request;

import lombok.Data;

/**
 * Форма регистрации
 */
@Data
public class RegisterRequest {

    /** Логин пользователя */
    private String username;
    /** Почта */
    private String email;
    /** Пароль */
    private String password;

}
