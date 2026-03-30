package com.example.demo.shared.controller;

import com.example.demo.shared.model.Tenant;
import com.example.demo.shared.repository.TenantRepository;
import com.example.demo.shared.service.TenantService;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class TenantRequest {
    private String name;

    public TenantRequest() {
    }

    public TenantRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

@RestController
@RequestMapping("/api/register-tenant")
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
