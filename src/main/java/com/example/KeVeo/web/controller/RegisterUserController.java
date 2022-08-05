package com.example.KeVeo.web.controller;

import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequestMapping("/register")
public class RegisterUserController {

   private UserService userService;

    //Aquí, en vez de esta linea es mejor utilizar @Autowired
    public  RegisterUserController(UserService userService){this.userService=userService;}


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
