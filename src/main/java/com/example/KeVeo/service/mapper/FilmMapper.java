package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.dto.FilmDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class FilmMapper extends AbstractServiceMapper<Film, FilmDTO>{

    private final ModelMapper modelMapper = new ModelMapper();

    public Film toEntity(FilmDTO filmDTO) {

        return modelMapper.map(filmDTO, Film.class);
    }

    public FilmDTO toDto(Film film) {

        return modelMapper.map(film, FilmDTO.class);
    }
}
