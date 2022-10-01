package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.dto.RoleDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RolMapper extends AbstractServiceMapper<Role, RoleDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public RoleDTO toDto(Role role) {

        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public Role toEntity(RoleDTO roleDTO) {

        return modelMapper.map(roleDTO, Role.class);

    }
}
