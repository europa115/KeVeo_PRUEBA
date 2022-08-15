package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT distinct f FROM Film f  JOIN f.genres g JOIN f.platforms p WHERE"
            +  " CONCAT(f.title,g.name,f.casting,p.name)"
            + " LIKE %?1%"
    )
    Page<Film> findAll(Pageable pageable, String wordKey);

}
