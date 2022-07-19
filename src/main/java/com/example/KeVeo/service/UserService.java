package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.MapperUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractBusinessService<User, Integer, UserDTO, UserRepository, MapperUser> {
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService(UserRepository repository, MapperUser serviceMapper, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        super(repository, serviceMapper);

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerDefaultUser(UserDTO userDTO) {
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        User entity=getServiceMapper().toEntity(userDTO);
        entity.addRole(roleUser);
        encodePassword(entity);
        getRepository().save(entity);
    }

    public void save(User user) {
        encodePassword(user);
        getRepository().save(user);

    }

    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }
}
