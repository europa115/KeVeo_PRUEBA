package com.example.KeVeo.web.controller;

import com.example.KeVeo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends AbstractController{
    @Autowired
    protected LoginController(MenuService menuService) {
        super(menuService);
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    // Login form with error
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("errors", true);
        return "login";
    }
}
