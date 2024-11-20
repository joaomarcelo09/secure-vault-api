package com.security_vault.adapters.repository;

import com.security_vault.domain.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PasswordRepository extends JpaRepository<Password, String> {

    @Query("FROM Password p WHERE p.user.id = :id_user")
    List<Password> findAllByIdUser(String id_user);
}
