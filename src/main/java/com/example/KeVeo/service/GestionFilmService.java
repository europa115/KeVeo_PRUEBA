package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.service.mapper.FilmServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GestionFilmService extends AbstractBusinessService<Film, Integer, FilmDTO, FilmRepository, FilmServiceMapper> {

    @Autowired
    protected GestionFilmService(FilmRepository repository, FilmServiceMapper serviceMapper) {
        super(repository, serviceMapper);
    }

    @Override
    public FilmDTO save(FilmDTO filmDTO){
       final Film entity=getServiceMapper().toEntity(filmDTO);
       return getServiceMapper().toDto(this.getRepository().save(entity));
    }
}
