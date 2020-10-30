package ru.sbrf.muza_service.web;

import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.muza_service.aspect.LogRequestResponse;
import ru.sbrf.muza_service.dao.entity.EUser;
import ru.sbrf.muza_service.exceptions.CustomException;
import ru.sbrf.muza_service.jwt.JwtTokenProvider;
import ru.sbrf.muza_service.service.EUserService;
import ru.sbrf.muza_service.web.reqponse.ErrorResponse;
import ru.sbrf.muza_service.web.reqponse.LoginResponse;
import ru.sbrf.muza_service.web.request.LoginRequest;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*" ,allowCredentials = "true",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PATCH, RequestMethod.DELETE})
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

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            EUser user = userService.getByUsername(loginRequest.getUsername());
            String token = jwtTokenProvider.createToken(user.getId(), loginRequest.getUsername(), user.getRoles());

            userService.setLastSign(user.getId());

            LoginResponse response = new LoginResponse();
            response.setUser_id(user.getId());
            response.setToken(token);

            return ResponseEntity.ok(response);
        }
        catch(CustomException | AuthenticationException | JwtException | IllegalArgumentException e) {
            log.error("Something wrong " + e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new ErrorResponse(e.getMessage()));
        }
    }

}
