package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.service.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FilmService extends AbstractBusinessService<Film,Integer, FilmDTO, FilmRepository, FilmMapper>{

    @Autowired
    protected FilmService(FilmRepository repository, FilmMapper serviceMapper) {
        super(repository, serviceMapper);
    }

    public Page<FilmDTO> findAll(Pageable pageable, String wordKey) {

        if (wordKey !=null){
            return this.getRepository().findAll(pageable,wordKey).map(getServiceMapper()::toDto);
        }
        return this.getRepository().findAll(pageable).map(getServiceMapper()::toDto);
    }
}
