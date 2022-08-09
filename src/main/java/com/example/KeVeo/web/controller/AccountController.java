package com.example.KeVeo.web.controller;


import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import com.example.KeVeo.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class AccountController extends AbstractController<UserDTO>{
    private UserMapper userMapper;
    private UserService userService;
    private UserRepository userRepository;
    @Autowired
    protected AccountController(MenuService menuService, UserMapper userMapper, UserService userService, UserRepository userRepository) {
        super(menuService);
        this.userMapper=userMapper;
        this.userService=userService;
        this.userRepository=userRepository;
    }

    @GetMapping("/account")
    public String getLogin() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();

        if(authentication==null){

            return "/login";

        }else return "account";

        //*********Puede que esto no funcione, revisar cuando este rol anonimo instaurado****************

    }
    @PostMapping({ "/{id}/account" })
    public Object changePhoto(@PathVariable(value = "id") Integer id, SessionStatus status,@RequestParam("file") MultipartFile photo) {
        try {
            UserDTO userDTO= this.userService.findById(id).get();
            userService.changePhoto(photo,userDTO);

        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("entityId", id)
                    .addObject("entityName", "user")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/gestionUser");
        }
        status.setComplete();
        return "redirect:/account";
    }

}
