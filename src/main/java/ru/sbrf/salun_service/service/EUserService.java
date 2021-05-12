package ru.sbrf.muza_service.service;

import ru.sbrf.muza_service.dao.entity.EUser;

public interface EUserService {

    EUser getById(Long userId);
    EUser getByUsername(String username);
    void setLastSign(Long id);

}
