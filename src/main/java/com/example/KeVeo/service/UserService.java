package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService/* extends UserDetailsService*/ {


    User save(UserDTO registroDTO);
}
