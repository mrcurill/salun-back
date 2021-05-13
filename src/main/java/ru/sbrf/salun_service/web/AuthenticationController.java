package ru.sbrf.salun_service.web;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.salun_service.aspect.LogRequestResponse;
import ru.sbrf.salun_service.dao.entity.EUser;
import ru.sbrf.salun_service.exceptions.CustomException;
import ru.sbrf.salun_service.jwt.JwtTokenProvider;
import ru.sbrf.salun_service.service.EUserService;
import ru.sbrf.salun_service.web.reqponse.ErrorResponse;
import ru.sbrf.salun_service.web.reqponse.LoginResponse;
import ru.sbrf.salun_service.web.request.LoginRequest;
import ru.sbrf.salun_service.web.request.RegisterRequest;


@RestController
@RequestMapping("/auth")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final EUserService userService;


    /**
     * Логин через стандартную форму
     * @param loginRequest логин и пароль пользователя
     * @return id пользователя и токен {@link LoginResponse}
     */
    @LogRequestResponse
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {

            //FIXME
            String username = loginRequest.getEmail().toUpperCase();

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, loginRequest.getPassword()));

            EUser user = userService.getByUsername(username);
            String token = jwtTokenProvider.createToken(user.getId(), username, user.getRoles());

            userService.setLastSign(user.getId());

            LoginResponse response = new LoginResponse();
            response.setUserId(user.getId());
            response.setToken(token);

            return ResponseEntity.ok(response);
        }
        catch(CustomException | AuthenticationException | JwtException | IllegalArgumentException e) {
            log.error("Something wrong " + e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
        }
    }

    @LogRequestResponse
    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody RegisterRequest request) {
        userService.register(request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
