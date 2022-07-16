package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	//@Query("SELECT r FROM Role r WHERE r.roleName = ?1")
	public Role findByRoleName(String name);
}
