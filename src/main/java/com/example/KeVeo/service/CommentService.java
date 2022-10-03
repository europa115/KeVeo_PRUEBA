package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Comment;
import com.example.KeVeo.data.repository.CommentRepository;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.CommentServiceMapper;
import com.example.KeVeo.service.mapper.UserServiceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService extends AbstractBusinessService<Comment,Integer, CommentDTO, CommentRepository, CommentServiceMapper> {

    private final UserServiceMapper userServiceMapper;

    @Autowired
    protected CommentService(CommentRepository repository, CommentServiceMapper serviceMapper, UserServiceMapper userServiceMapper) {
        super(repository, serviceMapper);

        this.userServiceMapper = userServiceMapper;
    }


    public List<CommentDTO> findByFilmId(Integer id){

        return getServiceMapper().toDto(getRepository().findByFilmId(id));

    }

    public UserDTO findByUserId(Integer id){

        return userServiceMapper.toDto(getRepository().findByUserId(id));
    }

}
