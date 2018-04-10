package com.vadimrostov.uyutp.web.controller;


import com.vadimrostov.uyutp.data.dao.UPArticlePostDAO;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.DeletePostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class UPAdminController {

    @Autowired
    UPArticlePostDAO upArticlePostDAO;

    @Autowired
    ModelAndView modelAndView;

    // ~TODO
    //СЕРВИС!!!
    @RequestMapping(value = "/deletepost", method = RequestMethod.GET)
    public @ResponseBody DeletePostResponse deletePost(@RequestParam String id){
        UPArticlePost articlePost=upArticlePostDAO.getArticle(Long.parseLong(id));
        articlePost.setDeleted(true);
        upArticlePostDAO.update(articlePost);
        return new DeletePostResponse(true, Long.parseLong(id));
    }
}
