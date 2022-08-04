package com.example.KeVeo.data.repository;

import com.example.KeVeo.data.entity.Menu;
import com.example.KeVeo.data.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Integer> {
    List<Menu> findDistinctByRolesIn(Collection<Role> roles);
}
