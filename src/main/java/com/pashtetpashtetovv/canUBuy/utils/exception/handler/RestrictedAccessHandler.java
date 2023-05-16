package com.pashtetpashtetovv.canUBuy.utils.exception.handler;

import com.pashtetpashtetovv.canUBuy.utils.exception.RestrictedAccessException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestrictedAccessHandler {

    @ExceptionHandler(RestrictedAccessException.class)
    public String handleRestrictedAccessException(RestrictedAccessException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }

}
