package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Comment;
import com.example.KeVeo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository  extends JpaRepository<Comment, Integer> {
    List<Comment> findByFilmId(Integer id);
    User findByUserId(Integer id);
}
