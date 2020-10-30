package ru.sbrf.muza_service.web.reqponse;


import lombok.Data;

/**
 * Ответ залогинившемуся пользователю
 */
@Data
public class LoginResponse {

    /** id пользователя */
    private Long user_id;
    /** jwt токен */
    private String token;

}

