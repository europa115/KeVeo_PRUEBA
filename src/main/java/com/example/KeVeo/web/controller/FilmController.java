package com.example.KeVeo.web.controller;

import com.example.KeVeo.data.entity.Film;
import com.example.KeVeo.data.entity.Genre;
import com.example.KeVeo.data.entity.Punctuation;
import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.CommentDTO;
import com.example.KeVeo.dto.FilmDTO;
import com.example.KeVeo.dto.PunctuationDTO;
import com.example.KeVeo.service.*;
import com.example.KeVeo.service.mapper.FilmMapper;
import com.example.KeVeo.service.mapper.PunctuationMapper;
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

    private final FilmService filmService;
    private final FilmMapper filmMapper;
    private final CommentService commentService;
    private final UserRepository userRepository;
    private final PunctuationService punctuationService;
    private final PunctuationMapper punctuationMapper;


    @Autowired
    protected FilmController(MenuService menuService, FilmService filmService, FilmMapper filmMapper,
                             CommentService commentService,UserRepository userRepository,
                             PunctuationService punctuationService,PunctuationMapper punctuationMapper) {
        super(menuService);

        this.filmService = filmService;
        this.filmMapper=filmMapper;
        this.commentService=commentService;
        this.userRepository=userRepository;
        this.punctuationService=punctuationService;
        this.punctuationMapper=punctuationMapper;

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
        Integer userId ;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            userId=3;
        }else userId=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        User user = userRepository.findById(userId).get();
        CommentDTO commentDTO=new CommentDTO();
        FilmDTO filmDTO = filmService.findById(id).get();
        Film film= filmMapper.toEntity(filmDTO);
        List<CommentDTO> listComments= commentService.findByFilmId(id);
        Double punctuationFilm=filmService.findFinalPunctuation(id);
        PunctuationDTO punctuationDTO=new PunctuationDTO();
        List<Punctuation> listPunctuations=filmService.findByPunctuations(id);

        model
                .addAttribute("film", film)
                .addAttribute("listComments",listComments)
                .addAttribute("listPunctuations",listPunctuations)
                .addAttribute("user",user)
                .addAttribute("punctuationFilm",punctuationFilm)
                .addAttribute("comment", commentDTO)
                .addAttribute("punctuation", punctuationDTO);

        return "film/filmInfo";
    }

    @PostMapping({ "/comment/delete/{idComment}/{idPunctuation}/{idFilm}" })
    public Object delete(@PathVariable(value = "idComment") Integer idComment,@PathVariable(value = "idPunctuation") Integer idPunctuation,
                         @PathVariable(value = "idFilm") Integer idFilm,
                         SessionStatus status) {
        try {
            this.commentService.delete(idComment);
            this.filmService.deletePunctuation(idFilm,idPunctuation);
            //Necesito borra primero registro de la tabla de films_punctuations para poder eliminar punctuation
            this.punctuationService.delete(idPunctuation);
        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", idComment)
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/film/filmInfo/{idFilm}");
        }
        status.setComplete();
        return "redirect:/film/filmInfo/{idFilm}";
    }

    @PostMapping({ "/favourite/agree/{id}" })
    public Object favourite(@PathVariable(value = "id") Integer id) {
        Integer userId ;
        if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
            userId=3;
        }else userId=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        User user = userRepository.findById(userId).get();
        user.addFavourite(filmMapper.toEntity(filmService.findById(id).get()));
        userRepository.save(user);


        return "redirect:/film/filmInfo/{id}?successful";
    }

   @PostMapping({ "/favourite/remove/{id}" })
    public Object quitFavourite(@PathVariable(value = "id") Integer id) {
       Integer userId ;
       if(SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")){
           userId=3;
       }else userId=((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
       User user = userRepository.findById(userId).get();
        userRepository.deleteFavourite(id,user.getId());

       return "redirect:/film/filmInfo/{id}?remove";
    }

    @PostMapping("/filmInfo/save/{id}")
    public String saveComment(CommentDTO commentDTO,PunctuationDTO punctuationDTO,@PathVariable("id") Integer id) {
        commentService.save(commentDTO);
        FilmDTO filmDTO =filmService.findById(id).get();
        Film entity=filmMapper.toEntity(filmDTO);
        entity.addPunctuation(punctuationMapper.toEntity(punctuationDTO));
        filmService.save(filmMapper.toDto(entity));
        return "redirect:/film/filmInfo/{id}";
    }

}
