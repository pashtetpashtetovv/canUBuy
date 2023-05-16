package com.pashtetpashtetovv.canUBuy.controller;

import com.pashtetpashtetovv.canUBuy.domain.model.User;
import com.pashtetpashtetovv.canUBuy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{login}")
    public String getUserPage(@PathVariable String login, Model model){
        userService.loadUserByUsername(login, model);
        return "user";
    }

//    @GetMapping("/me")
//    public String getMyPage(Model model, RedirectAttributes redirectAttributes){
//        final String authenticatedUserName = userService.getAuthenticatedUsername();
//        redirectAttributes.addAttribute("login", authenticatedUserName);
//        return "redirect:/user/{login}";
//    }

    @GetMapping("/signup")
    public String getSignUpPage(Model model){
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute User user){
        userService.create(user);
        return "redirect:/login";
    }

}
