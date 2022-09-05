package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.dto.FilmDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface FilmRepository extends JpaRepository<Film, Integer> {

    @Query("SELECT distinct f FROM Film f  JOIN f.genres g JOIN f.platforms p WHERE"
            +  " CONCAT(f.id,f.title,g.name,f.casting,p.name)"
            + " LIKE %?1% ORDER BY f.id"
    )
    Page<Film> findAll(Pageable pageable, String wordKey);

    @Query("SELECT f FROM Film f ORDER BY f.year DESC")
    List<Film> findByYear();
    @Query("SELECT f FROM Film f ORDER BY f.id DESC")
    List<Film> findByIdDesc();
    @Transactional
    @Query(value="SELECT f.* FROM film AS f INNER JOIN user_films AS uf ON f.id = uf.film_id INNER JOIN user AS u ON uf.user_id = u.id WHERE u.id LIKE %?1%", nativeQuery = true)
    Page<Film> findAllFavourite(Pageable pageable,Integer id);
    @Query(value="SELECT FLOOR(ROUND(AVG(p.score),1)*2+0.5)/2 FROM Punctuation AS p INNER JOIN films_punctuations AS fp ON p.id = fp.punctuation_id INNER JOIN film AS f ON fp.film_id = f.id WHERE f.id LIKE %?1%",
    nativeQuery = true)
    Double findFinalPunctuation(Integer id);

}
