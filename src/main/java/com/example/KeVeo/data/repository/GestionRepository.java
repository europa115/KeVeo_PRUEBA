package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.GestionUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionRepository extends JpaRepository<GestionUser, Integer> {

    Page<GestionUser> findByUserId(Integer userId, Pageable pageable);

}
