package com.security_vault.adapters.repository;

import com.security_vault.domain.model.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordRepository extends JpaRepository<Password, UUID> {
}
