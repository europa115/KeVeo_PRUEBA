package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUserEmailAndActiveTrue(String useremail);

    @Query("SELECT u FROM User u WHERE"
    + " CONCAT(u.id, u.userName, u.userEmail)"
    + " LIKE %?1%")
    Page<User> findAll(Pageable pageable,String wordKey);
    Page<User> findByActiveTrue(Pageable pageable);

    List<Film> findByFilms(Integer id);
    @Modifying
    @Transactional
    @Query(value="delete from user_films where film_id LIKE %?1% and user_id LIKE %?2%", nativeQuery = true)
    void deleteFavourite(Integer idFilm, Integer idUser);

}
