package com.portafolio.user_service.controller;


import com.portafolio.user_service.response.UserResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        return UserResponse.builder()
                .username(username)
                .build();
    }
}
