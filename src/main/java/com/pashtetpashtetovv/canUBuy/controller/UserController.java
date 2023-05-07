package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{login}")
    public String getUserPage(@PathVariable String login, Model model){
        final UserDetails user = userService.loadUserByUsername(login);
        model.addAttribute("user", user);
        return "user";
    }

}
