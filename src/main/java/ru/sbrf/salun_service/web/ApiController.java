package ru.sbrf.salun_service.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.sbrf.salun_service.service.EUserService;
import ru.sbrf.salun_service.web.dto.UserDto;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin
@RequiredArgsConstructor
@Slf4j
public class ApiController {

    private final EUserService userService;

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUser() {
        return ResponseEntity.ok(userService.getUserDtoList());
    }

    @PostMapping("/users")
    public ResponseEntity<Long> postCreateUser(@RequestBody UserDto request) {
        return ResponseEntity.ok(userService.createUser(request.getUsername()));
    }

}
