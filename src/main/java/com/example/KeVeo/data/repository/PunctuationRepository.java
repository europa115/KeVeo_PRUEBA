package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Punctuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PunctuationRepository extends JpaRepository<Punctuation,Integer> {

    @Modifying
    @Transactional
    @Query(value="delete from Punctuation where id LIKE %?1%", nativeQuery = true)
    void deletePunctuation(Integer id);
}
