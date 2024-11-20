package com.security_vault.application.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.configuration.MockAnnotationProcessor;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.security_vault.adapters.dto.PasswordGenerateResponseDto;
import com.security_vault.adapters.repository.PasswordRepository;
import com.security_vault.domain.model.Password;
import com.security_vault.domain.model.Users;

@ExtendWith(MockitoExtension.class)
public class PasswordServiceTest {

    @Mock
    private PasswordRepository passwordRepository;

    @Mock
    private UserService userService;

    @Autowired
    @InjectMocks
    private PasswordService passwordService;

    // @BeforeEach
    // void setup() {
    // MockitoAnnotations.initMocks(passwordService);
    // }

    @Test
    @DisplayName("Should create password successfully")
    void generatePassword() {
        Users user = new Users();

        user.setEmail("teste@teste.com");
        user.setName("testando");
        user.setPassword("123456");

        when(userService.findOne(user.getId(), null)).thenReturn(user);

        passwordService.createPassword("senha", user.getId());

        verify(passwordRepository, times(1)).save(any());
    }

}
