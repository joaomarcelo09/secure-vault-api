package com.security_vault.adapters.controller;

import com.security_vault.adapters.dto.PasswordGenerateDto;
import com.security_vault.adapters.dto.PasswordGenerateResponseDto;
import com.security_vault.application.service.PasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("password")
@RequiredArgsConstructor
public class PasswordController {

    @Autowired
    private final PasswordService passwordService;

    @PostMapping()
    public ResponseEntity<PasswordGenerateResponseDto> generatePassword(@RequestBody PasswordGenerateDto passwordGenerateDto, @RequestAttribute("user") String user) {
        return ResponseEntity.ok(passwordService.createPassword(passwordGenerateDto.title(), user));
    }
}
