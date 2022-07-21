package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.UserMapper;
import com.example.KeVeo.web.controller.AbstractController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

@Service
public class GestionUserService extends AbstractBusinessService<User,Integer,UserDTO, UserRepository, UserMapper> {

    @Autowired
    protected GestionUserService(UserRepository repository, UserMapper serviceMapper) {
        super(repository, serviceMapper);
    }

    @Override
    public UserDTO save(UserDTO dto) {
        final User entity = getServiceMapper().toEntity(dto);
        final User savedEntity = this.getRepository().save(entity);
        return getServiceMapper().toDto(savedEntity);
    }

    public Page<UserDTO> findAll(Integer userId, Pageable pageable) {
        return getRepository().findByUserId(userId, pageable).map(getServiceMapper()::toDto);
    }
}
