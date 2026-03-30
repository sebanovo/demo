package com.example.demo.shared.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.shared.model.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, String> {
}
