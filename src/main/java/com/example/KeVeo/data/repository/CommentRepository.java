package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository  extends JpaRepository<Comment, Integer> {
}
