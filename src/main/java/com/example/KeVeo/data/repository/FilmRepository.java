package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository  extends JpaRepository<Film, Integer> {
}
