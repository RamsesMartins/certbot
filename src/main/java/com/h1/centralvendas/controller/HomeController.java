package com.h1.centralvendas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @GetMapping
    public ModelAndView getFormLogin(HttpSession http){
        ModelAndView mvn = new ModelAndView("bootstrap/index");
        return mvn;
    }
}
