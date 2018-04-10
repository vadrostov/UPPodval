package com.vadimrostov.uyutp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {


    @Autowired
    ModelAndView modelAndView;

    @RequestMapping(value = "/login")
    public ModelAndView login(){


        modelAndView.setViewName("login");
        return modelAndView;
    }

}
