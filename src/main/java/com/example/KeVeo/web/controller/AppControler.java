package com.example.KeVeo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppControler {

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }


}
