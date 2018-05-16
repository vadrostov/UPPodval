package com.vadimrostov.uyutp.web.controller;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.service.UPCommentService;
import com.vadimrostov.uyutp.service.UPPostService;
import com.vadimrostov.uyutp.service.UPRoleService;
import com.vadimrostov.uyutp.service.UserService;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import com.vadimrostov.uyutp.web.dto.DateSearchDTO;
import com.vadimrostov.uyutp.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AngularController {


    UsernamePasswordAuthenticationToken token;
    @Autowired
    UPPostService upPostService;

    @Autowired
    UPCommentService upCommentService;

    @Autowired
    ModelAndView modelAndView;

    @Autowired
    UserService userService;

    @Autowired
    UPRoleService upRoleService;

    private static final String HOT_BEHAVIOR="date";


    @RequestMapping (value ="/angsec")
    public @ResponseBody
    Authentication getAuthBean(){

        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return authentication;

    }


    @RequestMapping (value = "/ang")
    public ModelAndView firstpage(){

        modelAndView.setViewName("showang");

        return modelAndView;
    }


    @RequestMapping (value = "/angfeed")
    public @ResponseBody List<ArticleDto> operateBehavior(){
        List<ArticleDto> dtoList=upPostService.getAllArticleDto(HOT_BEHAVIOR);

        return dtoList;
    }

    @RequestMapping (value = "/anguser")
    public @ResponseBody
    UserDto showUserInfo(@RequestParam String username){
        User user=userService.getByLogin(username);
        UserDto userDto=userService.getUserDto(user);
        return userDto;
    }

    @RequestMapping (value = "/angart")
    public @ResponseBody ArticleDto showArt(@RequestParam String id){
        long lid=Long.parseLong(id);
        UPArticlePost upArticlePost=upPostService.getArticlePost(lid);
        ArticleDto dto=upPostService.getArticleDto(upArticlePost);
        dto.setCommentDtoList(upCommentService.getCommentsDto(lid));
        return dto;

    }




}
