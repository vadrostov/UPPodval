package com.vadimrostov.uyutp.web.controller;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/feed")
public class UPFeedController {

    @Autowired
    UPPostService upPostService;

    ModelAndView modelAndView= new ModelAndView();

    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping("/new")
    public ModelAndView showNew(){
        List<UPArticlePost> posts=upPostService.getArticles();

        modelAndView.addObject("postList", posts);
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.setViewName("showfeed");
        return modelAndView;
    }


    @RequestMapping("/tag")
    public ModelAndView showHot(@RequestParam String tagname){
        List<UPArticlePost> posts=upPostService.getByTag(tagname);
        modelAndView.addObject("postList", posts);
        modelAndView.addObject("username", SecurityContextHolder.getContext().getAuthentication().getName());
        modelAndView.setViewName("showfeed");
        return modelAndView;
    }

    @RequestMapping("/rated")
    public ModelAndView showRated(){

        return modelAndView;
    }
}
