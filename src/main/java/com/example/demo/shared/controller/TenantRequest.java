package com.example.demo.shared.controller;

public class TenantRequest {
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
