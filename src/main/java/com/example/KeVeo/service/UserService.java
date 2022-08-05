package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class UserService extends AbstractBusinessService<User, Integer, UserDTO, UserRepository, UserMapper> {
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected UserService(UserRepository repository, UserMapper serviceMapper, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder) {
        super(repository, serviceMapper);

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerDefaultUser(UserDTO userDTO, MultipartFile photo){
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        User entity=getServiceMapper().toEntity(userDTO);
        if(!photo.isEmpty()){
            Path directoryImage= Paths.get("src//main//resources//static/imgUser");
            String AbsoluteRoute=directoryImage.toFile().getAbsolutePath();

            try{
                byte[] bytesImg= photo.getBytes();
                Path completRoute=Paths.get(AbsoluteRoute+"//"+photo.getOriginalFilename());
                Files.write(completRoute,bytesImg);

                entity.setPhoto(photo.getOriginalFilename());

            }catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
        entity.addRole(roleUser);
        entity.setActive(true);
        save(entity);

    }
    public void save(User user) {

        encodePassword(user);
        getRepository().save(user);

    }
    private void encodePassword(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    /*public Page<UserDTO> findByActiveTrue(Pageable pageable) {
            return getRepository().findByActiveTrue(pageable).map(getServiceMapper()::toDto);
    }*/

    public Page<UserDTO> findAll(Pageable pageable,String wordKey) {

        if (wordKey !=null){
            return this.getRepository().findAll(pageable,wordKey).map(getServiceMapper()::toDto);
        }
        return this.getRepository().findAll(pageable).map(getServiceMapper()::toDto);
    }

}
