package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.FilmService;
import com.example.KeVeo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;
@Controller
public class FilmController extends AbstractController<FilmDTO> {

    private FilmService filmService;

    @Autowired
    protected FilmController(MenuService menuService, FilmService filmService) {
        super(menuService);

        this.filmService = filmService;
    }

    @GetMapping("/film")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model, @Param("wordKey") String wordKey) {

        final Page<FilmDTO> listFilms = this.filmService.findAll(PageRequest.of(page.orElse(1) - 1,
                size.orElse(12)), wordKey);
        model
                .addAttribute("wordKey", wordKey)
                .addAttribute("listFilms", listFilms)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(listFilms));
        return "film";
    }
}
