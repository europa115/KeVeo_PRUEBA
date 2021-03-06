package com.example.KeVeo.service;

import com.example.KeVeo.data.entity.Menu;
import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.MenuRepository;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.MenuDTO;
import com.example.KeVeo.service.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class MenuService extends AbstractBusinessService<Menu, Integer, MenuDTO, MenuRepository, MenuMapper> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    protected MenuService(MenuRepository repository, MenuMapper serviceMapper,
                          UserRepository userRepository, RoleRepository roleRepository) {
        super(repository, serviceMapper);
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<MenuDTO> getMenuForUserId(Integer userId) {
        User user = this.userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException(String.format("The user ID %s does not exist", userId)));
        return getMenuForRole(user.getRoles());
    }

    public List<MenuDTO> getMenuForRole(Collection<Role> roles) {
        List<Menu> menus = this.getRepository().findDistinctByRolesIn(roles);
        return this.getServiceMapper().toDto(menus);
    }
}
