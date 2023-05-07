package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.config.security.role.Role;
import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registration")
    public String getRegistrationPage(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute User user){
        user.setPassword(
                passwordEncoder.encode(user.getPassword())
        );
        user.setRoles(
                Collections.singleton(Role.USER)
        );
        System.out.println(user);
        userService.create(user);
        return "redirect:/login";
    }

}
