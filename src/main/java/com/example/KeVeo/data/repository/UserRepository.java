package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Integer> {

   public User findByUserEmail(String useremail);

   User findByUserNameAndActiveTrue(String username);


}
