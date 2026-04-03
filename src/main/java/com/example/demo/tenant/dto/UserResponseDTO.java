package com.example.demo.tenant.dto;

import java.time.OffsetDateTime;

public class UserResponseDTO {
    private Long id;
    private String email;
    private Long roleId;
    private String roleName;
    private OffsetDateTime createdAt;

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String email, Long roleId, String roleName, OffsetDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
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
