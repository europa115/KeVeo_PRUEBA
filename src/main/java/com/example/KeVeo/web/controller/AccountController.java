package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.security.auth.kerberos.KerberosKey;

@Controller
public class AccountController extends AbstractController<UserDTO>{
    private UserMapper userMapper;
    private UserService userService;
    @Autowired
    protected AccountController(MenuService menuService, UserMapper userMapper, UserService userService) {
        super(menuService);
        this.userMapper=userMapper;
        this.userService=userService;
    }

    @GetMapping("/account")
    public String getLogin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if(authentication==null){

            return "/login";

        }else return "account";

        //*********Puede que esto no funcione, revisar cuando este rol anonimo instaurado****************

    }
    @PostMapping("/account/save")
    public String saveUser(UserDTO userDto) {

        User user=userMapper.toEntity(userDto);
        userService.save(user);

        return "redirect:/account";
    }

}
