package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.data.entity.Platform;
import com.example.KeVeo.data.repository.FilmRepository;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.service.FilmService;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.mapper.FilmMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
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
public class GestionFilmController extends AbstractController<FilmDTO> {

    private FilmService filmService;
    private FilmMapper filmMapper;
    private FilmRepository filmRepository;

    @Autowired
    protected GestionFilmController(MenuService menuService,FilmService filmService,FilmMapper filmMapper
            ,FilmRepository filmRepository) {
        super(menuService);
        this.filmService=filmService;
        this.filmMapper=filmMapper;
        this.filmRepository=filmRepository;
    }


    @GetMapping("/gestionFilm")
    public String getAll(@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
                         Model model, @Param("wordKey") String wordKey) {
        final Page<FilmDTO> listFilm = this.filmService.findAll(PageRequest.of(page.orElse(1) - 1,
                size.orElse(10)), wordKey);
        model
                .addAttribute("wordKey",wordKey)
                .addAttribute("listFilm", listFilm)
                .addAttribute(pageNumbersAttributeKey, getPageNumbers(listFilm));
        return "gestionFilm/listFilms";
    }
    @GetMapping("/gestionFilm/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap model) {
        FilmDTO filmDTO = filmService.findById(id).get();
        Film film= filmMapper.toEntity(filmDTO);
        List<Genre> listGenres = filmService.listGenres();
        List<Platform> listPlatforms= filmService.listPlatforms();
        model.addAttribute("film", film);
        model.addAttribute("listGenres", listGenres);
        model.addAttribute("listPlatforms", listPlatforms);

        return "gestionFilm/edit";
    }

    @GetMapping("gestionFilm/create")
    public String createFilm(ModelMap model){
        List<Genre> listGenres = filmService.listGenres();
        List<Platform> listPlatforms= filmService.listPlatforms();

        model.addAttribute("listGenres", listGenres);
        model.addAttribute("listPlatforms", listPlatforms);
        model.addAttribute("film", new FilmDTO());

        return"gestionFilm/create";
    }


    @GetMapping("gestionFilm/show/{id}")
    public String viewFilm(@PathVariable(value = "id") Integer id, ModelMap model){

        FilmDTO filmDTO = filmService.findById(id).get();
        Film film= filmMapper.toEntity(filmDTO);

        model.addAttribute("film", film);
        return "film/filmInfo";
    }

    //Es un borrado logico con active=FALSE
    @PostMapping({ "/gestionFilm/{id}" })
    public Object delete(@PathVariable(value = "id") Integer id, SessionStatus status) {
        try {
            FilmDTO filmDTO= this.filmService.findById(id).get();
            filmDTO.setActive(false);
            filmService.save(filmDTO);

        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", id)
                    .addObject("entityName", "filmDTO")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/gestionFilm");
        }
        status.setComplete();
        return "redirect:/gestionFilm";
    }

    @PostMapping("/gestionFilm/save")
    public String saveUser(FilmDTO filmDTO) {

        filmService.save(filmDTO);

        return "redirect:/gestionFilm";
    }
}
