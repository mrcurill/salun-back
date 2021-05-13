package ru.sbrf.salun_service.dao.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.sbrf.salun_service.dao.entity.EUser;

import java.util.List;
import java.util.Optional;

@Repository
public interface EUserRepository extends CrudRepository<EUser, Long> {

    Optional<EUser> findByUsernameAndIsDeletedFalse(String username);
    List<EUser> findByIsDeletedFalse();

}
