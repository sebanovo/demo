package com.example.demo.tenant.dto;

import java.time.OffsetDateTime;

public class UserResponseDTO {
    private String id;
    private String email;
    private String roleId;
    private String roleName;
    private OffsetDateTime createdAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(String id, String email, String roleId, String roleName, OffsetDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
