package com.vadimrostov.uyutp.web.controller;



import com.vadimrostov.uyutp.data.domain.Comment;
import com.vadimrostov.uyutp.data.domain.Like;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPCommentService;
import com.vadimrostov.uyutp.service.UPLikeService;
import com.vadimrostov.uyutp.service.UPPostService;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
@RequestMapping("/article")
public class UPArticleController {

    ModelAndView modelAndView= new ModelAndView();

    @Autowired
    UPLikeService upLikeService;

    @Autowired
    UPPostService upPostService;

    @Autowired
    UPCommentService upCommentService;


    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addArticle(){
        modelAndView.addObject("createdArticle", new UPArticlePost());
        modelAndView.setViewName("articleadmin");
        return modelAndView;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveArticle(UPArticlePost upArticlePost){

        upArticlePost.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        upArticlePost.setDate(new Date());
        upPostService.saveArticlePost(upArticlePost);
        modelAndView.setViewName("redirect:/article/"+upArticlePost.getId());
        return modelAndView;
    }

    @RequestMapping(value="/{articleId}", method = RequestMethod.GET)
    public ModelAndView showArticle(@PathVariable String articleId){
        Long articleLongId=Long.parseLong(articleId);
        UPArticlePost upArticlePost=upPostService.getArticlePost(articleLongId);
        modelAndView.addObject("comments", upArticlePost.getComments());
        modelAndView.addObject("createdComment", new Comment());

        modelAndView.addObject("rate", upPostService.getRate(articleLongId));


        modelAndView.addObject("article", upArticlePost);
        modelAndView.setViewName("showarticle");
        return modelAndView;
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.POST)
    public ModelAndView addComment(Comment comment, @PathVariable String articleId){
        comment.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        comment.setUpArticlePost(upPostService.getArticlePost(Long.parseLong(articleId)));
        comment.setTimestamp(new Date());
        upCommentService.saveComment(comment);
        return modelAndView;
    }


    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public @ResponseBody
    Response getAjax(@RequestParam String text, @RequestParam String val, @RequestParam String username){

        Long id = Long.parseLong(text);
        UPArticlePost upArticlePost=upPostService.getArticlePost(id);
        Like like=new Like();
        boolean likeexist=false;

        for (Like liken: upArticlePost.getLike()){

            if(liken.getAuthor().equals(username)){

                like=liken;

                likeexist=true;
            }
        }
        like.setAuthor(username);
        like.setDate(new Date());
        like.setUpArticlePost(upArticlePost);
        if (val.equals("dec")){
        like.setValue(false);}
        else like.setValue(true);
        if (likeexist){
           upLikeService.update(like);}
        else upLikeService.save(like);
        Response response=new Response();
        response.setRate(upPostService.getRate(id));
        response.setId(id);
        return response;



    }


}
