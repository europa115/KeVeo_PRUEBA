package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
   // @Query("SELECT u FROM User u WHERE userEmail = ?1")
    public User findByUserEmailAndActiveTrue(String useremail);

    User findByUserNameAndActiveTrue(String username);

   // Page<User> findByUserId(Pageable pageable);

    Page<User> findAll(Pageable pageable);

}
