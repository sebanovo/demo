package com.example.demo.multitenant;

public final class TenantContext {
    private static final ThreadLocal<String> CURRENT_TENANT = ThreadLocal.withInitial(() -> "public");

    private TenantContext() {
    }

    public static void setTenant(String tenant) {
        if (tenant == null || tenant.isBlank()) {
            CURRENT_TENANT.set("public");
        } else {
            CURRENT_TENANT.set(tenant);
        }
    }

    public static String getTenant() {
        String t = CURRENT_TENANT.get();
        return t == null ? "public" : t;
    }

    public static void clear() {
        CURRENT_TENANT.remove();
    }
}
