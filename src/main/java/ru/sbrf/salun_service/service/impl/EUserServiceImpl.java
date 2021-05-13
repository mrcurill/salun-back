package ru.sbrf.salun_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sbrf.salun_service.dao.entity.EUser;
import ru.sbrf.salun_service.dao.repository.EUserRepository;
import ru.sbrf.salun_service.exceptions.CustomException;
import ru.sbrf.salun_service.exceptions.UserIdNotFoundException;
import ru.sbrf.salun_service.exceptions.UsernameNotFoundException;
import ru.sbrf.salun_service.service.EUserService;
import ru.sbrf.salun_service.web.dto.UserDto;
import ru.sbrf.salun_service.web.mapper.UserMapper;
import ru.sbrf.salun_service.web.request.RegisterRequest;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EUserServiceImpl implements EUserService {

    private final EUserRepository userRepository;
    private final UserMapper userMapper;

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

    @Override
    public UserDto getUserDto(Long userId) {
        return userMapper.mapToUserDto(getById(userId));
    }

    @Override
    public List<UserDto> getUserDtoList() {
        return userMapper.mapToListUserDto(userRepository.findByIsDeletedFalse());
    }

    @Override
    public Long createUser(String username) {

        if( userRepository.findByUsernameAndIsDeletedFalse(username.toUpperCase()).isPresent() )
            throw new CustomException("Пользователь уже существует");

        EUser user = new EUser();
        user.setCreatedBy(1L);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedBy(1L);
        user.setUpdatedAt(LocalDateTime.now());
        user.setUsername(username.toUpperCase());
        userRepository.save(user);

        return user.getId();
    }

    @Override
    public void register(RegisterRequest request) {

        //FIXME on login
        String username = request.getEmail().toUpperCase();

        if( userRepository.findByUsernameAndIsDeletedFalse(username).isPresent() )
            throw new CustomException("Пользователь уже существует");

        EUser user = new EUser();
        user.setCreatedBy(1L);
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedBy(1L);
        user.setUpdatedAt(LocalDateTime.now());
        user.setUsername(username);
        user.setPassword(request.getPassword());
        userRepository.save(user);
    }

}
