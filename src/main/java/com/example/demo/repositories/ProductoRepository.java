package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.tenant.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
