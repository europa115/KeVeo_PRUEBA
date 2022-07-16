package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserServiceMapper extends AbstractServiceMapper<User, UserDTO> {

    private final RoleServiceMapper roleServiceMapper;

    @Autowired
    public UserServiceMapper(RoleServiceMapper roleServiceMapper) {
        this.roleServiceMapper = roleServiceMapper;
    }

    @Override
    public User toEntity(UserDTO dto) {

        final User entity = new User();
        entity.setId(dto.getId());
        entity.setUserName(dto.getUserName());
        entity.setUserSurname(dto.getUserSurname());
        entity.setPassword(dto.getPassword());
        entity.setActive(dto.isActive());
        entity.setUserEmail(dto.getUserEmail());
        entity.setDate(dto.getDate());
       // entity.setRoles(this.roleServiceMapper.toEntity(new ArrayList<>(dto.getRoles())));

        return entity;
    }

    @Override
    public UserDTO toDto(User entity) {

        final UserDTO dto = new UserDTO();

        dto.setId(dto.getId());
        dto.setUserName(dto.getUserName());
        dto.setUserSurname(dto.getUserName());
        dto.setPassword(dto.getPassword());
        dto.setActive(dto.isActive());
        dto.setUserEmail(dto.getUserEmail());
        dto.setDate(dto.getDate());
        //dto.setRoles(this.roleServiceMapper.toDto(new ArrayList<>(entity.getRoles())));

        return dto;
    }
}
