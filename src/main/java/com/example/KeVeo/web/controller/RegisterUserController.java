package com.example.KeVeo.web.controller;

import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterUserController extends AbstractController{

   private UserService userService;
    @Autowired
    protected RegisterUserController(MenuService menuService, UserService userService) {
        super(menuService);
        this.userService=userService;
    }

    @ModelAttribute("user")
    public UserDTO returnNewUserDTO() {

        return new UserDTO();
    }

    @GetMapping
    public String viewFormUser() {

            return "register";

    }
    @PostMapping
    public String registerAccountUser(@ModelAttribute("user") UserDTO userDTO, @RequestParam("file")  MultipartFile photo){

        userService.registerDefaultUser(userDTO,photo);

        return "redirect:/register?successful";
    }

}
