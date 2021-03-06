package com.vadimrostov.uyutp.web.controller;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/")
public class HomeController {

    @Autowired
    ModelAndView modelAndView;

    @Autowired
    UPPostService upPostService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView home(){
        modelAndView.setViewName("redirect:/feed/new");
        return modelAndView;
    }
}
