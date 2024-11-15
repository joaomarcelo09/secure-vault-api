package com.security_vault.adapters.controller;

import com.security_vault.adapters.controller.exception.TooManyRequest;
import com.security_vault.adapters.dto.CreateUserDto;
import com.security_vault.adapters.dto.CreateUserResponseDto;
import com.security_vault.adapters.dto.EditUserResponseDto;
import com.security_vault.adapters.dto.FindOneResponseDto;
import com.security_vault.adapters.dto.LoginUserDto;
import com.security_vault.adapters.dto.LoginUserResponseDto;
import com.security_vault.application.service.PasswordService;
import com.security_vault.application.service.UserService;
import com.security_vault.domain.model.Users;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final PasswordService passwordService;
    private final Bucket bucket;

    @Autowired
    public UserController(UserService userService, PasswordService passwordService) {
        this.userService = userService;
        this.passwordService = passwordService;
        Bandwidth limit = Bandwidth.classic(5, Refill.greedy(5, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }

    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserResponseDto> register(@RequestBody CreateUserDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @PatchMapping()
    public ResponseEntity<EditUserResponseDto> editUser(@RequestBody CreateUserDto user,
            @RequestAttribute("user") String userID) {
        return ResponseEntity.ok(userService.editUser(userID, user));
    }

    @GetMapping("/my")
    public ResponseEntity<FindOneResponseDto> findOne(@RequestAttribute("user") String userID) {
        Users user = userService.findOne(userID, null);

        return ResponseEntity.ok(new FindOneResponseDto(user.getName(), user.getEmail()));
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteUser(@RequestAttribute("user") String userID) {

        passwordService.deleteAllPasswords(userID);
        userService.deleteUser(userID);
        return ResponseEntity.ok("Deletado com sucesso");
    }

    @PostMapping("/auth")
    public ResponseEntity<LoginUserResponseDto> loginUser(@RequestBody LoginUserDto user) {

        if (!bucket.tryConsume(1)) {
            throw new TooManyRequest();
        }

        return ResponseEntity.ok(userService.loginUser(user));
    }
}
