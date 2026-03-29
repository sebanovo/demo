package com.example.demo.controllers;

import com.example.demo.models.shared.Tenant;
import com.example.demo.repositories.TenantRepository;
import com.example.demo.services.TenantService;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register-tenant")
public class TenantAdminController {
    private final TenantService tenantService;
    private final TenantRepository tenantRepository;

    public TenantAdminController(TenantService tenantService, TenantRepository tenantRepository) {
        this.tenantService = tenantService;
        this.tenantRepository = tenantRepository;
    }

    @PostMapping
    public ResponseEntity<String> createTenant(@RequestBody TenantRequest request) {
        String name = request.getName();
        // Creando el esquema
        tenantService.createTenant(name);

        // Guardando el tenant en la base de datos public tabla (tenant)
        Tenant tenant = new Tenant();
        tenant.setId(name);
        tenant.setName(name);
        tenant.setPaidUntil(LocalDate.now().plusDays(30));
        tenant.setOnTrial(true);
        tenant.setActive(true);
        tenantRepository.save(tenant);

        return ResponseEntity.status(201).body("Tenant creado: " + name);
    }
}
