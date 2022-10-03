package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.Platform;
import com.example.KeVeo.data.entity.Punctuation;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.data.repository.GenreRepository;
import com.example.KeVeo.data.repository.PlatformRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.dto.GenreDTO;
import com.example.KeVeo.dto.PunctuationDTO;
import com.example.KeVeo.service.mapper.FilmServiceMapper;
import com.example.KeVeo.service.mapper.GenreServiceMapper;
import com.example.KeVeo.service.mapper.PunctuationServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilmService extends AbstractBusinessService<Film,Integer, FilmDTO, FilmRepository, FilmServiceMapper>{

    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
    private final PunctuationServiceMapper punctuationServiceMapper;
    private final GenreServiceMapper genreServiceMapper;
    private final UserRepository userRepository;

    @Autowired
    protected FilmService(FilmRepository repository, FilmServiceMapper serviceMapper, GenreRepository genreRepository,
                          PlatformRepository platformRepository, PunctuationServiceMapper punctuationServiceMapper, GenreServiceMapper genreServiceMapper, UserRepository userRepository) {
        super(repository, serviceMapper);

        this.genreRepository=genreRepository;
        this.platformRepository=platformRepository;
        this.punctuationServiceMapper = punctuationServiceMapper;
        this.genreServiceMapper = genreServiceMapper;
        this.userRepository=userRepository;
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

    public List<GenreDTO> listGenres() {
        return genreServiceMapper.toDto(genreRepository.findAll());
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

    public List<PunctuationDTO> findByPunctuations(Integer id){

        List<Object[]> listObjects= getRepository().findByPunctuations(id);
        List<Punctuation> listPunctuations=new ArrayList<>();

        for(Object [] obj:listObjects){

            Punctuation pnt=new Punctuation((Integer) obj[0], (Double) obj[1],userRepository.findById((Integer) obj[2]).get());

            listPunctuations.add(pnt);
        }

        return punctuationServiceMapper.toDto(listPunctuations);
    }

    public void deletePunctuation(Integer idFilm, Integer idPuncuation){
        getRepository().deletePunctuation(idFilm,idPuncuation);
    }

}
