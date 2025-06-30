package com.portafolio.user_service.repository;

import com.portafolio.user_service.entity.User;
import com.portafolio.user_service.response.UserResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(String email);

}
