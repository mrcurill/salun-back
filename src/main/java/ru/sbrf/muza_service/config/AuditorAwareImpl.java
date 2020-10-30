package ru.sbrf.muza_service.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.sbrf.muza_service.jwt.JwtUser;
import ru.sbrf.muza_service.jwt.JwtUserFactory;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {

        JwtUser jwtUser;
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if( securityContext.getAuthentication() == null || securityContext.getAuthentication().getPrincipal().equals("anonymousUser") )
            jwtUser = JwtUserFactory.createAdmin();
        else
            jwtUser = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return Optional.ofNullable(jwtUser.getId());
    }
}
