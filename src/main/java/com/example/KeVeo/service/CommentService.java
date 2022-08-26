package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Comment;
import com.example.KeVeo.data.repository.CommentRepository;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.service.mapper.CommentMapper;
import com.example.KeVeo.service.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends AbstractBusinessService<Comment,Integer, CommentDTO, CommentRepository, CommentMapper> {

    FilmRepository filmRepository;
    FilmMapper filmMapper;

    @Autowired
    protected CommentService(CommentRepository repository, CommentMapper serviceMapper, FilmRepository filmRepository,
                             FilmMapper filmMapper) {
        super(repository, serviceMapper);

        this.filmRepository=filmRepository;
        this.filmMapper=filmMapper;
    }


    public List<CommentDTO> findByFilmId(Integer Id){

        return getServiceMapper().toDto(getRepository().findByFilmId(Id));

    }

}
