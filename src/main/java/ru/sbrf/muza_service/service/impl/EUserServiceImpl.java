package ru.sbrf.muza_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sbrf.muza_service.dao.entity.EUser;
import ru.sbrf.muza_service.dao.repository.EUserRepository;
import ru.sbrf.muza_service.exceptions.UserIdNotFoundException;
import ru.sbrf.muza_service.exceptions.UsernameNotFoundException;
import ru.sbrf.muza_service.service.EUserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EUserServiceImpl implements EUserService {

    private final EUserRepository userRepository;

    /**
     * Найти пользователя по Id
     * @param userId id пользователя
     * @return пользователь {@link EUser}
     */
    @Override
    public EUser getById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserIdNotFoundException(userId));
    }

    /**
     * Найти пользователя по логину
     * @param username логин пользователя
     * @return пользователь {@link EUser}
     */
    @Override
    public EUser getByUsername(String username) {
        return userRepository.findByUsernameAndIsDeletedFalse(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    /**
     * Проставить на пользователе дату последего входа
     * @param id id пользователя
     */
    @Override
    public void setLastSign(Long id) {
        EUser user = getById(id);
        user.setLastSign(LocalDateTime.now());
        userRepository.save(user);
    }

}
