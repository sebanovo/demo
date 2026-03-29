package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.shared.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, String> {
}
