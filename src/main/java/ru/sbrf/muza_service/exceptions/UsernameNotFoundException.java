package ru.sbrf.muza_service.exceptions;

public class UsernameNotFoundException extends CustomException{

    public UsernameNotFoundException(String username) {
        super("Пользователь с логином '".concat(username).concat("' не найден."));
    }
}
