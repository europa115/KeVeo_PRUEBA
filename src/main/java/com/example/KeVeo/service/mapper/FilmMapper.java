package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.dto.FilmDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class FilmMapper extends AbstractServiceMapper<Film, FilmDTO>{

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Film toEntity(FilmDTO filmDTO) {

        return modelMapper.map(filmDTO, Film.class);
    }

    @Override
    public FilmDTO toDto(Film film) {

        return modelMapper.map(film, FilmDTO.class);
    }
}
