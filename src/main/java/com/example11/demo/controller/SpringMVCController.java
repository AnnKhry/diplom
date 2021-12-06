package com.example11.demo.controller;

import com.example11.demo.model.enumerations.User;
import com.example11.demo.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class SpringMVCController {
UserRepository userRepository;

        @RequestMapping(value = "/username", method = RequestMethod.GET)

    public String foo(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username = user.getUsername();
        model.addAttribute("username", username);
        return "username";
    }

}
