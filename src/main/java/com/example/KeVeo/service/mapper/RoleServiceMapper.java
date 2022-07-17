package com.example.KeVeo.service.mapper;


import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.dto.RoleDTO;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceMapper extends AbstractServiceMapper<Role, RoleDTO> {

    //*********Falta registerDate en todos los mapper***********************************************

    public Role toEntity(RoleDTO dto) {
        final Role entity = new Role();
        entity.setId(dto.getId());
        entity.setRoleName(dto.getRoleName());
        return entity;
    }

    public RoleDTO toDto(Role entity) {
        final RoleDTO dto = new RoleDTO();
        dto.setId(entity.getId());
        dto.setRoleName(entity.getRoleName());
        return dto;
    }
}
