package com.security_vault.application.service;

import com.security_vault.adapters.dto.CreateUserDto;
import com.security_vault.adapters.dto.CreateUserResponseDto;
import com.security_vault.domain.exception.UserNotFound;
import com.security_vault.domain.model.Users;
import com.security_vault.infrastructure.repository.UserRepository;
import com.security_vault.infrastructure.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public Users findOne(String id, String email) {
        return userRepository.findByIdOrEmail(id, email).orElseThrow(UserNotFound::new);
    }

    public CreateUserResponseDto createUser(CreateUserDto user) {

        Users newUser = new Users();
        newUser.setEmail(user.email());
        newUser.setPassword(passwordEncoder.encode(user.password()));
        newUser.setName(user.name());

        String token = tokenService.generateToken(newUser);

        userRepository.save(newUser);

        return new CreateUserResponseDto(
                newUser.getName(), newUser.getEmail(),token
        );
    }
}
