package ru.sbrf.salun_service.service;

import ru.sbrf.salun_service.dao.entity.EUser;
import ru.sbrf.salun_service.web.dto.UserDto;
import ru.sbrf.salun_service.web.request.RegisterRequest;

import java.util.List;

public interface EUserService {

    EUser getById(Long userId);
    EUser getByUsername(String username);
    void setLastSign(Long id);

    UserDto getUserDto(Long userId);
    List<UserDto> getUserDtoList();
    Long createUser(String username);

    void register(RegisterRequest request);

}
