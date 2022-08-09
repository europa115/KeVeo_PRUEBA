package com.example.KeVeo.web.controller;

import com.example.KeVeo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControler extends AbstractController{

    @Autowired
    protected HomeControler(MenuService menuService) {
        super(menuService);
    }

    @GetMapping("/")
    public String viewHomePage() {
        return "home";
    }




}
