package com.security_vault.adapters.controller;

import com.security_vault.adapters.dto.CreateUserDto;
import com.security_vault.adapters.dto.CreateUserResponseDto;
import com.security_vault.application.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService userService;


    @PostMapping("/auth/register")
    public ResponseEntity<CreateUserResponseDto> register(@RequestBody CreateUserDto user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
