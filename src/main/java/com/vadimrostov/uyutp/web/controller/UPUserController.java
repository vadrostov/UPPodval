package com.vadimrostov.uyutp.web.controller;

import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UPUserController {

    @Autowired
    ModelAndView modelAndView;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{userlogin}")
    public ModelAndView showUserInfo(@PathVariable String userlogin){
        User user=userService.getByLogin(userlogin);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("showUser");
        return modelAndView;

    }

}
