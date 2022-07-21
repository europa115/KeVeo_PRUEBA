package com.example.KeVeo.web.controller;


import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class GestionUserController extends AbstractController<UserDTO>{

    private UserService userService;

    @Autowired
    protected GestionUserController(MenuService menuService,UserService userService) {
        super(menuService);
        this.userService=userService;
    }


    @GetMapping("/gestionUser")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model) {
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        final Page<UserDTO> all = this.userService.findAll(PageRequest.of(page.orElse(1) - 1,
                size.orElse(10)));
        model
                .addAttribute("username", user.getUserName())
                .addAttribute("users", all)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(all));
        return "gestionUser/list";
    }
   /* @GetMapping("/gestionUser/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        User user = userService.get(id);
        List<Role> listRoles = service.listRoles();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
        service.save(user);

        return "redirect:/users";
    }*/
}