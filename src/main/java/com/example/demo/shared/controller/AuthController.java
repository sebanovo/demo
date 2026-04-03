package com.example.demo.shared.controller;

import com.example.demo.shared.dto.AuthRequest;
import com.example.demo.shared.dto.AuthResponse;
import com.example.demo.shared.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService service;
    private final com.example.demo.tenant.service.UserService userService;

    public AuthController(AuthenticationService service, com.example.demo.tenant.service.UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticate(
            @RequestBody AuthRequest request
    ) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/signup")
    public ResponseEntity<com.example.demo.tenant.dto.UserResponseDTO> register(
            @RequestBody com.example.demo.tenant.dto.UserRequestDTO request
    ) {
        return ResponseEntity.status(201).body(userService.createUser(request));
    }
}
