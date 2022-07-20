package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.GestionUser;
import com.example.KeVeo.dto.GestionDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GestionMapper extends AbstractServiceMapper<GestionUser, GestionDTO> {

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public GestionUser toEntity(GestionDTO gestionDTO) {
        return modelMapper.map(gestionDTO, GestionUser.class);
    }

    @Override
    public GestionDTO toDto(GestionUser gestionUser) {
        return modelMapper.map(gestionUser, GestionDTO.class);
    }
}
