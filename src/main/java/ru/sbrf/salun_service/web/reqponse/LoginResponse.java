package ru.sbrf.salun_service.web.reqponse;


import lombok.Data;

/**
 * Ответ залогинившемуся пользователю
 */
@Data
public class LoginResponse {

    /** id пользователя */
    private Long userId;
    /** jwt токен */
    private String token;

}

