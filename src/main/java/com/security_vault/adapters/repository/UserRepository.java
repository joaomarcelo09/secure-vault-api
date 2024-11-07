package com.security_vault.adapters.repository;

import com.security_vault.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, String> {

    Optional<Users> findByIdOrEmail(String id, String email);
}
