package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Platform;
import com.example.KeVeo.dto.PlatformDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PlatformServiceMapper extends AbstractServiceMapper<Platform, PlatformDTO>{

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Platform toEntity(PlatformDTO platformDTO) {
        return modelMapper.map(platformDTO, Platform.class);
    }

    @Override
    public PlatformDTO toDto(Platform platform) {
        return modelMapper.map(platform, PlatformDTO.class);
    }
}
