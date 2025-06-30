package com.portafolio.user_service.controller;

import com.portafolio.user_service.entity.Role;
import com.portafolio.user_service.entity.User;
import com.portafolio.user_service.repository.RoleRepository;
import com.portafolio.user_service.repository.UserRepository;
import com.portafolio.user_service.request.LoginRequest;
import com.portafolio.user_service.request.RegisterRequest;
import com.portafolio.user_service.response.AuthResponse;
import com.portafolio.user_service.security.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        // Buscamos el rol USER (deberías tenerlo cargado en la DB con ese nombre)
        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(Set.of(userRole)) // 👈 asignamos el rol
                .build();

        userRepository.save(user);

        Map<String, Object> claims = Map.of(
                "roles", user.getRoles().stream().map(Role::getName).toList()
        );
        var jwt = jwtUtil.generateToken(claims, user.getUsername());


        return ResponseEntity.ok(new AuthResponse(jwt));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        );

        authenticationManager.authenticate(authToken);

        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId()); // 👈 importante
        claims.put("roles", user.getRoles().stream()
                .map(Role::getName)
                .toList());

        var jwt = jwtUtil.generateToken(claims, request.getUsername());
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    }

