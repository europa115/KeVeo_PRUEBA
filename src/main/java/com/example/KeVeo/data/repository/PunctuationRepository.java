package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Punctuation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PunctuationRepository extends JpaRepository<Punctuation,Integer> {

}
