package com.example.KeVeo.web.controller;



import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.RoleDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
public class GestionUserController extends AbstractController<UserDTO>{

    private UserService userService;
    private RoleRepository roleRepository;
    private UserMapper userMapper;

    private UserRepository userRepository;


    @Autowired
    protected GestionUserController(MenuService menuService,UserService userService,RoleRepository roleRepository,
                                    UserMapper userMapper,UserRepository userRepository) {
        super(menuService);
        this.userService=userService;
        this.roleRepository=roleRepository;
        this.userMapper=userMapper;
        this.userRepository=userRepository;
    }


    @GetMapping("/gestionUser")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final Page<UserDTO> listUsers = this.userService.findByActiveTrue(PageRequest.of(page.orElse(1) - 1,
                size.orElse(10)));
        model
                .addAttribute("username", user.getUserName())
                .addAttribute("listUsers", listUsers)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(listUsers));
        return "gestionUser/listUser";
    }
    @GetMapping("/gestionUser/edit/{id}")
        public String edit(@PathVariable("id") Integer id, ModelMap model) {
        UserDTO userDto = userService.findById(id).get();
        User user= userMapper.toEntity(userDto);
        List<Role> listRoles = userService.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);

        return "gestionUser/edit";
    }

//El metodo de borrar no esta hecho exactamente para borrar sino lo que hace es cambiar la actividad a 0/false
    @PostMapping({ "/gestionUser/{id}/delete" })
    public Object delete(@PathVariable(value = "id") Integer id, SessionStatus status) {
        try {
           UserDTO userDTO= this.userService.findById(id).get();
           User user=userMapper.toEntity(userDTO);
           user.setActive(false);
           userRepository.save(user);

        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", id)
                    .addObject("entityName", "user")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/gestionUser");
        }
        status.setComplete();
        return "redirect:/gestionUser";
    }

    @PostMapping("/gestionUser/save")
    public String saveUser(UserDTO userDto) {

        User user=userMapper.toEntity(userDto);
        userService.save(user);

        return "redirect:/gestionUser";
    }
}