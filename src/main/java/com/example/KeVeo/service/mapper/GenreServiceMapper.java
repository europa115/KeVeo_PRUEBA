package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.dto.GenreDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceMapper extends AbstractServiceMapper<Genre, GenreDTO>{

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Genre toEntity(GenreDTO genreDTO) {
        return modelMapper.map(genreDTO, Genre.class);
    }

    @Override
    public GenreDTO toDto(Genre genre) {
        return modelMapper.map(genre, GenreDTO.class);
    }
}
