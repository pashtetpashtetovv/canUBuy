package com.pashtetpashtetovv.canUBuy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping({"/", "/main", "/index"})
    public String getIndex(){
        return "index";
    }
}
