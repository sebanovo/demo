package com.example.demo.multitenant;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class TenantResolutionFilter extends OncePerRequestFilter {
    public static final String TENANT_HEADER = "X-Tenant-ID";

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();
        String method = request.getMethod();

        // Allow creating tenants without tenant header
        if ("/tenant".equals(path) && "POST".equalsIgnoreCase(method)) {
            try {
                filterChain.doFilter(request, response);
            } finally {
                TenantContext.clear();
            }
            return;
        }

        String tenant = resolveTenantFromRequest(request);
        if (tenant == null || tenant.isBlank() || "public".equalsIgnoreCase(tenant)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":\"Tenant requerido en X-Tenant-ID o subdominio\"}");
            return;
        }

        try {
            TenantContext.setTenant(tenant);
            filterChain.doFilter(request, response);
        } finally {
            TenantContext.clear();
        }
    }

    private String resolveTenantFromRequest(HttpServletRequest request) {
        String tenant = request.getHeader(TENANT_HEADER);
        if (tenant != null && !tenant.isBlank()) {
            return tenant.trim();
        }

        String host = request.getHeader("Host");
        if (host != null && host.contains(".")) {
            String subdomain = host.split("\\.")[0];
            if (!subdomain.isBlank() && !subdomain.equalsIgnoreCase("localhost")) {
                return subdomain;
            }
        }

        return "public";
    }
}
