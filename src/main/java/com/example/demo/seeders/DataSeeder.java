package com.example.demo.seeders;

import com.example.demo.controllers.ProductoController;
import com.example.demo.models.tenant.Producto;
import com.example.demo.repositories.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DataSeeder(ProductoRepository productoRepository, ProductoController productoController) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        // seedersProducto();
    }

    public void seedersProducto() {
        if (productoRepository.count() != 0)
            return;
        String izquierda = ">".repeat(20);
        String derecha = "<".repeat(20);
        String mensaje = izquierda + "Seeder Ejecutado" + derecha;
        System.out.println(mensaje);
        productoRepository.save(new Producto(null, "Laptop", 1200.50, null));
        productoRepository.save(new Producto(null, "Mouse", 25.00, null));
        productoRepository.save(new Producto(null, "Teclado", 45.00, null));
    }
}
