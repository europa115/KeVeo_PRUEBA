package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.service.FilmService;
import com.example.KeVeo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeControler extends AbstractController{

    private FilmService filmService;

    @Autowired
    protected HomeControler(MenuService menuService, FilmService filmService) {
        super(menuService);
        this.filmService=filmService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {

            final List<Genre> listGenres=filmService.listGenres();
            final List<FilmDTO> listFilms = this.filmService.findAll();
            final List<FilmDTO> listFilmsYear=this.filmService.findByYear();
        final List<FilmDTO> listFilmsIdDesc=this.filmService.findByIdDesc();
            model
                    .addAttribute("listFilms", listFilms)
                    .addAttribute("listFilmsYear", listFilmsYear)
                    .addAttribute("listFilmsIdDesc", listFilmsIdDesc)
                    .addAttribute("listGenres", listGenres);

        return "home";
    }

}
