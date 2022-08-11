package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatformRepository  extends JpaRepository<Platform, Integer> {
}
