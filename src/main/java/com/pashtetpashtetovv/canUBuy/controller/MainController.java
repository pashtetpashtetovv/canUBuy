package com.pashtetpashtetovv.canUBuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/", "/main", "/index", "/home"})
    public String getIndex(){
        return "index";
    }

    @GetMapping("/about")
    public String getAbout(){
        return "about";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

    @GetMapping
    public String getEmpty(){
        return "empty";
    }

    @GetMapping("/success")
    public String getSuccess(){
        return "success";
    }

}
