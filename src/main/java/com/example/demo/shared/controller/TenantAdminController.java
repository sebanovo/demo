package com.example.demo.shared.controller;

import com.example.demo.shared.model.Tenant;
import com.example.demo.shared.repository.TenantRepository;
import com.example.demo.shared.service.TenantService;
import com.example.demo.tenant.model.Role;
import com.example.demo.tenant.model.User;
import com.example.demo.tenant.repository.RoleRepository;
import com.example.demo.tenant.repository.UserRepository;
import com.example.demo.multitenant.TenantContext;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

class TenantRequest {
    private String name;
    private String adminEmail;
    private String adminPassword;

    public TenantRequest() {
    }

    public TenantRequest(String name, String adminEmail, String adminPassword) {
        this.name = name;
        this.adminEmail = adminEmail;
        this.adminPassword = adminPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminEmail() {
        return adminEmail;
    }

    public void setAdminEmail(String adminEmail) {
        this.adminEmail = adminEmail;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}

@RestController
@RequestMapping("/api/register-tenant")
public class TenantAdminController {

    private final TenantService tenantService;
    private final TenantRepository tenantRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public TenantAdminController(TenantService tenantService, TenantRepository tenantRepository, 
                                 UserRepository userRepository, RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder) {
        this.tenantService = tenantService;
        this.tenantRepository = tenantRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<String> createTenant(@RequestBody TenantRequest request) {
        String name = request.getName();
        String encodedPassword = passwordEncoder.encode(request.getAdminPassword());
        
        // Creando el esquema, ejecutando migraciones Flyway y creando el admin (vía JDBC)
        tenantService.createTenant(name, request.getAdminEmail(), encodedPassword);

        // Guardando el tenant en la base de datos public tabla (tenant)
        Tenant tenant = new Tenant();
        tenant.setId(name);
        tenant.setName(name);
        tenant.setPaidUntil(LocalDate.now().plusDays(30));
        tenant.setOnTrial(true);
        tenant.setActive(true);
        tenantRepository.save(tenant);

        return ResponseEntity.status(201).body("Tenant y usuario admin creados para: " + name);
    }
}
