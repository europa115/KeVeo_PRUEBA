package com.example.KeVeo.service.mapper;

import com.example.KeVeo.data.entity.Comment;
import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.dto.FilmDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CommnetMapper extends AbstractServiceMapper<Comment, CommentDTO>{

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Comment toEntity(CommentDTO commentDTO) {
        return modelMapper.map(commentDTO, Comment.class);
    }

    @Override
    public CommentDTO toDto(Comment comment) {
        return modelMapper.map(comment, CommentDTO.class);
    }
}
