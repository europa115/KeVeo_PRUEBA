package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.UserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionUserService extends AbstractBusinessService<User,Integer,UserDTO, UserRepository, UserServiceMapper> {

    @Autowired
    protected GestionUserService(UserRepository repository, UserServiceMapper serviceMapper) {
        super(repository, serviceMapper);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        final User entity = getServiceMapper().toEntity(dto);
        final User savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

}
