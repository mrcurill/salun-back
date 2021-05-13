package ru.sbrf.salun_service.exceptions;

public class UserIdNotFoundException extends CustomException{

    public UserIdNotFoundException(Long userId) {
        super("Пользователь с id '".concat(userId.toString()).concat("' не найден."));
    }
}
