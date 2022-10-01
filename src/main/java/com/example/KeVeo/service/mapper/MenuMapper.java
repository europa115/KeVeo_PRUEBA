package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Menu;

import com.example.KeVeo.dto.MenuDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class MenuMapper extends AbstractServiceMapper<Menu, MenuDTO> {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Menu toEntity(MenuDTO menuDTO) {
        return modelMapper.map(menuDTO, Menu.class);
    }

    @Override
    public MenuDTO toDto(Menu menu) {
        return modelMapper.map(menu, MenuDTO.class);
    }
}
