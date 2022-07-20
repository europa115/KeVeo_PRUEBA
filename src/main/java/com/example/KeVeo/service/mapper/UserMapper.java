package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;


@Service
public class UserMapper extends AbstractServiceMapper<User, UserDTO> {
    private ModelMapper modelMapper = new ModelMapper();

    public UserDTO toDto(User user) {

        return modelMapper.map(user, UserDTO.class);

    }

    public User toEntity(UserDTO userDTO) {

        return modelMapper.map(userDTO, User.class);

    }

}


