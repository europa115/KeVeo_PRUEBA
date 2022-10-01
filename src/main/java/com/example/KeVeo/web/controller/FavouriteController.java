package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.FilmDTO;

import com.example.KeVeo.service.FilmService;
import com.example.KeVeo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class FavouriteController extends AbstractController<FilmDTO>{
    private final FilmService filmService;

    @Autowired
    protected FavouriteController(MenuService menuService,FilmService filmService) {
        super(menuService);
        this.filmService=filmService;
    }
    @GetMapping("/favourite")
    public String favouritesView(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                                 Model model){
        Integer userId ;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            userId=3;
        }else userId=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        final Page<FilmDTO> listFavourites = this.filmService.findAllFavourite(PageRequest.of(page.orElse(1) - 1,
                        size.orElse(12)),userId);
        model
                .addAttribute("listFavourites",listFavourites)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(listFavourites));

        return "favourite";
    }
}
