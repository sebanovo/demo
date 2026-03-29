package com.example.demo.controllers;

import com.example.demo.services.TenantService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    public ResponseEntity<String> createTenant(@RequestBody TenantRequest request) {
        String name = request.getName();
        tenantService.createTenant(name);
        return ResponseEntity.status(201).body("Tenant creado: " + name);
    }
}
