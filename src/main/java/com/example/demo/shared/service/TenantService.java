package com.example.demo.shared.service;

import org.flywaydb.core.Flyway;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Service
public class TenantService {
    private final DataSource dataSource;

    public TenantService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createTenant(String tenant) {
        if (tenant == null || !tenant.matches("[a-zA-Z0-9_]+")) {
            throw new IllegalArgumentException("Nombre de tenant inválido. Use solo letras, números y guiones bajos.");
        }

        try (Connection conn = dataSource.getConnection(); Statement stmt = conn.createStatement()) {
            String sql = "CREATE SCHEMA IF NOT EXISTS \"" + tenant + "\"";
            stmt.execute(sql);
        } catch (Exception e) {
            throw new RuntimeException("Error creando el schema: " + e.getMessage(), e);
        }

        // Ejecuta migraciones Flyway contra el schema recién creado
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .schemas(tenant)
                .locations("classpath:db/migration/tenant")
                .baselineOnMigrate(true)
                .load();

        flyway.migrate();
    }
}
