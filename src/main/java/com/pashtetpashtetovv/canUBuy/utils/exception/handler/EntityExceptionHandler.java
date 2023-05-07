package com.pashtetpashtetovv.canUBuy.utils.exception.handler;

import com.pashtetpashtetovv.canUBuy.utils.exception.NotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public String handleNotFoundException(NotFoundException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUsernameNotFoundException(UsernameNotFoundException e, Model model){
        model.addAttribute("message", e.getMessage());
        return "error";
    }

}
