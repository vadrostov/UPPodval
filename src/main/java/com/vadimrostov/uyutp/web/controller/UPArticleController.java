package com.vadimrostov.uyutp.web.controller;



import com.vadimrostov.uyutp.data.domain.Comment;
import com.vadimrostov.uyutp.data.domain.Like;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.UPCommentService;
import com.vadimrostov.uyutp.service.UPImageService;
import com.vadimrostov.uyutp.service.UPLikeService;
import com.vadimrostov.uyutp.service.UPPostService;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.Response;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;

@Controller
@RequestMapping("/article")
public class UPArticleController {

    private final static String ROLE_USER="ROLE_USER";
    private final static String ROLE_ADMIN="ROLE_ADMIN";

    ModelAndView modelAndView= new ModelAndView();

    @Autowired
    UPImageService upImageService;

    @Autowired
    UPLikeService upLikeService;

    @Autowired
    UPPostService upPostService;

    @Autowired
    UPCommentService upCommentService;



    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView addArticle(){
        modelAndView.addObject("createdArticle", new ArticleDto());
        modelAndView.setViewName("articleadmin");
        return modelAndView;
    }
    @PreAuthorize("hasRole('ROLE_USER')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ModelAndView saveArticle(ArticleDto articleDto){
        try{
            String formatedContent=upImageService.convertContent(articleDto.getContent());
            articleDto.setContent(formatedContent);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        articleDto.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        articleDto.setDate(new Date());

        UPArticlePost upArticlePost=upPostService.saveNewPost(articleDto);
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


    @Secured(ROLE_USER)
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
