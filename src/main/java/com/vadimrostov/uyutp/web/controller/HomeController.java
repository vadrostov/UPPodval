package com.vadimrostov.uyutp.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ModelAndView modelAndView;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(){
        modelAndView.addObject("date", new Date());
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
