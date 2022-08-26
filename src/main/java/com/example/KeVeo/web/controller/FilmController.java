package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.data.entity.Role;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.GenreRepository;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.CommentService;
import com.example.KeVeo.service.FilmService;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
@Controller
public class FilmController extends AbstractController<FilmDTO> {

    private FilmService filmService;

    private GenreRepository genreRepository;

    private FilmMapper filmMapper;

    private CommentService commentService;

    @Autowired
    protected FilmController(MenuService menuService, FilmService filmService,GenreRepository genreRepository,
                             FilmMapper filmMapper,CommentService commentService) {
        super(menuService);

        this.filmService = filmService;
        this.genreRepository=genreRepository;
        this.filmMapper=filmMapper;
        this.commentService=commentService;
    }

    @GetMapping("/film")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model, @Param("wordKey") String wordKey) {
        final List<Genre> listGenres=filmService.listGenres();
        final Page<FilmDTO> listFilms = this.filmService.findAll(PageRequest.of(page.orElse(1) - 1,
                size.orElse(12)), wordKey);
        model
                .addAttribute("wordKey", wordKey)
                .addAttribute("listFilms", listFilms)
                .addAttribute("listGenres", listGenres)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(listFilms));
        return "film/filmAll";
    }

    @GetMapping("/film/filmInfo/{id}")
    public String viewInfo(@PathVariable("id") Integer id, ModelMap model) {

        FilmDTO filmDTO = filmService.findById(id).get();
        Film film= filmMapper.toEntity(filmDTO);
        final User user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        List<CommentDTO> listComments= commentService.findByFilmId(id);

        model
                .addAttribute("film", film)
                .addAttribute("user", user)
                .addAttribute("listComments",listComments);

        return "film/filmInfo";
    }

}
