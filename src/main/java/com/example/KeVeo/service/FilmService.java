package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.data.entity.Platform;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.data.repository.GenreRepository;
import com.example.KeVeo.data.repository.PlatformRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.service.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmService extends AbstractBusinessService<Film,Integer, FilmDTO, FilmRepository, FilmMapper>{

    private GenreRepository genreRepository;
    private PlatformRepository platformRepository;

    @Autowired
    protected FilmService(FilmRepository repository, FilmMapper serviceMapper,GenreRepository genreRepository,
                          PlatformRepository platformRepository) {
        super(repository, serviceMapper);

        this.genreRepository=genreRepository;
        this.platformRepository=platformRepository;
    }

    public Page<FilmDTO> findAll(Pageable pageable, String wordKey) {

        if (wordKey !=null){
            return this.getRepository().findAll(pageable,wordKey).map(getServiceMapper()::toDto);
        }
        return this.getRepository().findAll(pageable).map(getServiceMapper()::toDto);
    }

    public List<FilmDTO> findByYear(){

        return getServiceMapper().toDto(this.getRepository().findByYear());

    }

    public List<FilmDTO> findByIdDesc(){

        return getServiceMapper().toDto(this.getRepository().findByIdDesc());

    }

    public List<Genre> listGenres() {
        return genreRepository.findAll();
    }

    public List<Platform> listPlatforms() {
        return platformRepository.findAll();
    }

    public Page<FilmDTO> findAllFavourite(Pageable pageable, Integer id) {

        return this.getRepository().findAllFavourite(pageable,id).map(getServiceMapper()::toDto);
    }

    public Double findFinalPunctuation(Integer id){

        return getRepository().findFinalPunctuation(id);

    }
}
