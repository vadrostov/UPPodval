package com.vadimrostov.uyutp.web.controller;

import com.vadimrostov.uyutp.service.UPPostService;
import com.vadimrostov.uyutp.web.bean.UPSearchBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class UPSearchController {

    @Autowired
    UPPostService upPostService;

    @Autowired
    ModelAndView modelAndView;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchSome(@Valid UPSearchBean searchBean, BindingResult result){

        modelAndView.addObject("postList", upPostService.searchPost(searchBean.getSearchStr()));
        modelAndView.setViewName("showfeedj");
        return modelAndView;


    }


}
