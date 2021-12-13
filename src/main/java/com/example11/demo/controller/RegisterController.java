package com.example11.demo.controller;





import com.example11.demo.model.enumerations.Role;
import com.example11.demo.model.exceptions.*;
import com.example11.demo.service.AuthService;
import com.example11.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final AuthService authService;
    private final UserService userService;

    public RegisterController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("title","Register");
        model.addAttribute("bodyContent","register");

        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String email,
                           @RequestParam String phone,
                           @RequestParam Role role,
                           Model model) {
        try {
            this.userService.register(username, password, repeatedPassword, name, surname,email,phone,role);
            return "redirect:/login";
        } catch (InvalidArgumentsException | PasswordsDoNotMatchException | EmailAlreadyExistsException | InvalidEmailException | UsernameAlreadyExistsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "redirect:/register?error=" + exception.getMessage();
        }
    }
}
