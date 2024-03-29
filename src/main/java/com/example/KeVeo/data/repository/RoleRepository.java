package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	Role findByRoleName(String name);
}
