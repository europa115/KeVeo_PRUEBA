package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Comment;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.CommentRepository;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.CommentMapper;
import com.example.KeVeo.service.mapper.FilmMapper;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends AbstractBusinessService<Comment,Integer, CommentDTO, CommentRepository, CommentMapper> {

    FilmRepository filmRepository;
    FilmMapper filmMapper;

    UserMapper userMapper;

    @Autowired
    protected CommentService(CommentRepository repository, CommentMapper serviceMapper, FilmRepository filmRepository,
                             FilmMapper filmMapper,UserMapper userMapper) {
        super(repository, serviceMapper);

        this.filmRepository=filmRepository;
        this.filmMapper=filmMapper;
        this.userMapper=userMapper;
    }


    public List<CommentDTO> findByFilmId(Integer id){

        return getServiceMapper().toDto(getRepository().findByFilmId(id));

    }

    public UserDTO findByUserId(Integer id){

        return userMapper.toDto(getRepository().findByUserId(id));
    }

}
