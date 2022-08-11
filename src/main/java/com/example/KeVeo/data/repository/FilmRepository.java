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
    @Query("SELECT u FROM User u WHERE"
            + " CONCAT(u.id, u.userName, u.userEmail)"
            + " LIKE %?1%")
    Page<Film> findAll(Pageable pageable, String wordKey);


}
