package com.vadimrostov.uyutp.web.controller;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/feed")
public class UPFeedController {

    @Autowired
    UPPostService upPostService;

    ModelAndView modelAndView= new ModelAndView();

    @RequestMapping("/new")
    public ModelAndView showNew(){
        List<UPArticlePost> posts=upPostService.getArticles();
        modelAndView.addObject("postList", posts);

        modelAndView.setViewName("showfeed");
        return modelAndView;
    }


    @RequestMapping("/hot")
    public ModelAndView showHot(){

        return modelAndView;
    }

    @RequestMapping("/rated")
    public ModelAndView showRated(){

        return modelAndView;
    }
}
