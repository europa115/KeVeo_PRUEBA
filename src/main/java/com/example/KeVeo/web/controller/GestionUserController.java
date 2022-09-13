package com.example.KeVeo.web.controller;



import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.RoleRepository;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
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
    private UserMapper userMapper;

    @Autowired
    protected GestionUserController(MenuService menuService,UserService userService, UserMapper userMapper) {
        super(menuService);

        this.userService=userService;
        this.userMapper=userMapper;
    }

    @GetMapping("/gestionUser")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model, @Param("wordKey") String wordKey) {

        final Page<UserDTO> listUsers = this.userService.findAll(PageRequest.of(page.orElse(1) - 1,
                size.orElse(10)), wordKey);
        model
                .addAttribute("wordKey",wordKey)
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

//Creacion de un borrado logico
    @PostMapping({ "/gestionUser/{id}" })
    public Object delete(@PathVariable(value = "id") Integer id, SessionStatus status) {
        try {
           UserDTO userDTO= this.userService.findById(id).get();
           userDTO.setActive(false);
           this.userService.save(userDTO);

        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", id)
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/gestionUser");
        }
        status.setComplete();
        return "redirect:/gestionUser";
    }

    @PostMapping("/gestionUser/save")
    public String saveUser(UserDTO userDto) {

        userService.save(userMapper.toEntity(userDto));

        return "redirect:/gestionUser";
    }
}