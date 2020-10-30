package ru.sbrf.muza_service.jwt;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ru.sbrf.muza_service.dao.entity.ERole;
import ru.sbrf.muza_service.dao.entity.EUser;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public static JwtUser createAdmin() {
        return new JwtUser(
                1L,
                "admin",
                null,
                null,
                null,
                null,
                Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")),
                true,
                LocalDateTime.now()
        );
    }

    public static JwtUser create(EUser user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                mapToGrantedAuthority(user.getRoles()),
                !user.getIsDeleted(),
                user.getUpdatedAt()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthority(List<ERole> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}

