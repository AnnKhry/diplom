package com.example11.demo.controller;


import com.example11.demo.model.enumerations.User;
import com.example11.demo.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


@Controller
public class MainController {

    private final AuthService authService;

    public MainController(AuthService authService) {
        this.authService =authService;
    }

    @GetMapping("/")
    public String home( Model model) {
        model.addAttribute("title", "Главная страница");
        return "home";
    }

        @GetMapping("/about")
        public String about( Model model) {
            model.addAttribute("title", "О нас");
            return "about";
    }


    @GetMapping("/userAccount")
    public String getUserAccount(HttpServletRequest req, Model model){
        String username = req.getRemoteUser();
        System.out.println(username);
        Optional<User> user = this.authService.findByUsername(username);

        model.addAttribute("user",user);
        model.addAttribute("title","UserAccount");
        model.addAttribute("bodyContent","userAccount");

        return "/userAccount";
    }

    @GetMapping("/userAccount/settings")
    public String getSettings(HttpServletRequest req,Model model){
        String username = req.getRemoteUser();

        Optional<User> user = this.authService.findByUsername(username);

        model.addAttribute("user",user);

        model.addAttribute("title","Settings");
        model.addAttribute("bodyContent","settings");

        return "master-template";

    }

    @PostMapping("/userAccount/{username}")
    public String update(@PathVariable String username,
                         @RequestParam String usernameOfUser,
                         @RequestParam String nameOfUser,
                         @RequestParam String lastnameOfUser,
                         @RequestParam String emailOfUser){
        this.authService.update(username,usernameOfUser, nameOfUser, lastnameOfUser, emailOfUser);
        return "redirect:/userAccount";

    }

    @PostMapping("/user/delete/{username}")
    public String delete(@PathVariable String username){
        this.authService.delete(username);
        return "redirect:/home";
    }



}