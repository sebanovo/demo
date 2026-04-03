package com.example.demo.tenant.service;

import com.example.demo.tenant.dto.RoleRequestDTO;
import com.example.demo.tenant.dto.RoleResponseDTO;
import com.example.demo.tenant.model.Role;
import com.example.demo.tenant.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<RoleResponseDTO> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(role -> new RoleResponseDTO(role.getId(), role.getName()))
                .collect(Collectors.toList());
    }

    public RoleResponseDTO getRoleById(String id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
        return new RoleResponseDTO(role.getId(), role.getName());
    }

    public RoleResponseDTO createRole(RoleRequestDTO request) {
        Role role = new Role();
        role.setId(request.getId()); // Usualmente se envía como 'ROLE_NAME'
        role.setName(request.getName());
        role = roleRepository.save(role);
        return new RoleResponseDTO(role.getId(), role.getName());
    }

    public void deleteRole(String id) {
        roleRepository.deleteById(id);
    }
}
