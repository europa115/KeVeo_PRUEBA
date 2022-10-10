package com.example.KeVeo.web.controller;


import com.example.KeVeo.data.entity.User;
import com.example.KeVeo.data.repository.UserRepository;
import com.example.KeVeo.dto.UserDTO;
import com.example.KeVeo.service.MenuService;
import com.example.KeVeo.service.UserService;
import com.example.KeVeo.service.mapper.UserServiceMapper;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController extends AbstractController<UserDTO> {
    private final UserServiceMapper userServiceMapper;
    private final UserService userService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    protected AccountController(MenuService menuService, UserServiceMapper userServiceMapper, UserService userService, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        super(menuService);
        this.userServiceMapper = userServiceMapper;
        this.userService = userService;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping("/account")
    public String getLogin(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDTO userDTO = this.userService.findById(((User) authentication.getPrincipal()).getId()).get();
        model.addAttribute("user",userDTO);

        return "account/account-info";
    }

    @PostMapping({"/{id}/account"})
    public Object changePhoto(@PathVariable(value = "id") Integer id, SessionStatus status, @RequestParam("file") MultipartFile photo) {
        try {
            UserDTO userDTO = this.userService.findById(id).get();
            userService.changePhoto(photo, userDTO);

        } catch (DataIntegrityViolationException exception) {
            status.setComplete();
            return new ModelAndView("error/errorHapus")
                    .addObject("errorCause", exception.getRootCause().getMessage())
                    .addObject("backLink", "/gestionUser");
        }
        status.setComplete();

        return "redirect:/account";
    }

    @GetMapping("/{id}/account/changePassword")
    public String viewChangePassword(@PathVariable(value = "id") Integer id) {

        return "account/changePassword";
    }

    @PostMapping("/changePassword")
    public String changePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, Model model) {

        UserDTO userDTO = userService.findById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();

        if (bCryptPasswordEncoder.matches(oldPassword, userDTO.getPassword())) {
            userDTO.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.save(userDTO);
            model.addAttribute("successful", true);
        } else {
            model.addAttribute("errors", true);
            return "/account/changePassword";
        }
        return "account/changePassword";

    }

    @PostMapping({"/deleteAccount"})
    public String deleteAccount(){
        UserDTO userDTO = userService.findById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId()).get();
        userDTO.setActive(false);
        userService.save(userDTO);
        return "redirect:/logout";
    }

}
