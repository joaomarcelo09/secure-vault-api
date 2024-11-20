package com.security_vault.adapters.repository;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import com.security_vault.domain.model.Password;
import com.security_vault.domain.model.Users;
import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;

@DataJpaTest
@ActiveProfiles("test")
class PasswordRepositoryTest {

    @Autowired
    PasswordRepository passwordRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Should get Password successfully fromm DB")
    void findAllByIdUser() {

        String id_user = registerPasswordWithUser();

        List<Password> passwords = passwordRepository.findAllByIdUser(id_user);

        assertThat(passwords.isEmpty()).isFalse();
    }

    private String registerPasswordWithUser() {

        Users newUser = new Users();
        newUser.setName("UserTeste");
        newUser.setPassword("teste123");
        newUser.setEmail("teste@teste.com.br");

        entityManager.persist(newUser);

        Password newPassword = new Password();
        newPassword.setName("Teste");
        newPassword.setPassword("1235jal");
        newPassword.setUser(newUser);

        this.entityManager.persist(newPassword);

        return newUser.getId();

    }
}
