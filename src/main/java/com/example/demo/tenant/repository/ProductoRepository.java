package com.example.demo.tenant.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.tenant.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
