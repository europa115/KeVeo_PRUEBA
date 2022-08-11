package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository  extends JpaRepository<Genre, Integer> {
}
