package com.example.KeVeo.web.controller;


import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.dto.MenuDTO;
import com.example.KeVeo.service.MenuService;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public abstract class AbstractController<DTO> {

    private final MenuService menuService;
    protected final String pageNumbersAttributeKey = "pageNumbers";

    protected AbstractController(MenuService menuService) {
        this.menuService = menuService;
    }

    @ModelAttribute("menuList")
    public List<MenuDTO> menu() {
        final Integer userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return this.menuService.getMenuForUserId(userId);

    }

    protected List<Integer> getPageNumbers(Page<DTO> pages) {
        return pages.getTotalPages() > 0 ?
                IntStream.rangeClosed(1, pages.getTotalPages()).boxed().collect(Collectors.toList()) :
                new ArrayList<>();
    }
}
