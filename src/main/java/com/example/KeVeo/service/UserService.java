package com.example.KeVeo.service;


import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.mapper.FilmMapper;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class UserService extends AbstractBusinessService<User, Integer, UserDTO, UserRepository, UserMapper> {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final FilmMapper filmMapper;
    private final AWSS3ServiceImpl awss3Service;


    @Autowired
    protected UserService(UserRepository repository, UserMapper serviceMapper, RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,FilmMapper filmMapper,AWSS3ServiceImpl awss3Service) {
        super(repository, serviceMapper);

        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.filmMapper= filmMapper;
        this.awss3Service=awss3Service;
    }

    public void registerDefaultUser(UserDTO userDTO, MultipartFile photo){
        Role roleUser = roleRepository.findByRoleName("ROLE_USER");
        User entity=getServiceMapper().toEntity(userDTO);
        if(!photo.isEmpty()){
            awss3Service.uploadFile(photo);
            entity.setPhoto(this.awss3Service.getNewFileName());
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

    public void changePhoto(MultipartFile photo, UserDTO userDTO){
        if(!photo.isEmpty()){
            awss3Service.uploadFile(photo);
            userDTO.setPhoto(this.awss3Service.getNewFileName());
            getRepository().save(getServiceMapper().toEntity(userDTO));

        }
    }

    public Page<UserDTO> findAll(Pageable pageable,String wordKey) {

        if (wordKey !=null){
            return this.getRepository().findAll(pageable,wordKey).map(getServiceMapper()::toDto);
        }
        return this.getRepository().findAll(pageable).map(getServiceMapper()::toDto);
    }

    List<FilmDTO> findByFilms(Integer id){

        return filmMapper.toDto(getRepository().findByFilms(id));
    }

}
