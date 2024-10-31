package com.security_vault.infrastructure.repository;

import com.security_vault.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

    Optional<Users> findByIdOrEmail(String id, String email);
}
