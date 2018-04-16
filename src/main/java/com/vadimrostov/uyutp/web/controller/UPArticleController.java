package com.vadimrostov.uyutp.web.controller;



import com.vadimrostov.uyutp.data.domain.UPComment;
import com.vadimrostov.uyutp.data.domain.UPPostLike;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.service.*;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.ArticleCards;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.DeletePostResponse;
import com.vadimrostov.uyutp.web.controller.web.jsonbeans.Response;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import com.vadimrostov.uyutp.web.dto.CommentDto;
import com.vadimrostov.uyutp.web.dto.FlowDto;
import com.vadimrostov.uyutp.web.dto.LikeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.Date;

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

    @Autowired
    UPTagService tagService;

    @Autowired
    UPPostCategoryService categoryService;

    private String INCREMENT_VAL="inc";
    private String DECREMET_VAL="dec";



    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView redirectToFlow(){
       /* modelAndView.addObject("createdArticle", new ArticleDto());
        modelAndView.setViewName("articleadmin");
        return modelAndView;*/
        FlowDto flowDto=new FlowDto();
        flowDto.setCategoryDtos(categoryService.getCategoryDtos());
        flowDto.setTagDtos(tagService.getTags());

       modelAndView.addObject("createdArticle", new ArticleDto());
       modelAndView.setViewName("redirect:/post");
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
        modelAndView.addObject("commenttree", upCommentService.getCommentsDto(articleLongId));
        //modelAndView.addObject("comments", upArticlePost.getUPComments());
        modelAndView.addObject("createdComment", new UPComment());

        modelAndView.addObject("rate", upPostService.getRate(articleLongId));


        modelAndView.addObject("article", upArticlePost);
        modelAndView.setViewName("showarticle");
        return modelAndView;
    }

    @RequestMapping(value = "/{articleId}", method = RequestMethod.POST)
    public ModelAndView addComment(UPComment UPComment, @PathVariable String articleId){
        UPComment.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        UPComment.setUpArticlePost(upPostService.getArticlePost(Long.parseLong(articleId)));
        UPComment.setTimestamp(new Date());
        upCommentService.saveComment(UPComment);
        return modelAndView;
    }

    @RequestMapping(value = "/cards", method = RequestMethod.GET)
    public @ResponseBody
    ArticleCards commentView(@RequestParam String ids){
       String[] id=ids.split(" ");
       int[] intids= new int[id.length];
       for(int i=0; i<id.length; i++){
           intids[i]=Integer.parseInt(id[i]);
       }
        return new ArticleCards(upPostService.getCards(intids));
    }


    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public @ResponseBody
    DeletePostResponse deletePost(@RequestParam String id){
        UPArticlePost articlePost=upPostService.getArticlePost(Long.parseLong(id));
        articlePost.setDeleted(true);
        upPostService.update(articlePost);
        return new DeletePostResponse(true, Long.parseLong(id));
    }


    @RequestMapping(value = "/commentrate", method = RequestMethod.GET)
    public @ResponseBody Response likeComment(@RequestParam String text, @RequestParam String val){
        boolean bval;
        if(val.equals(INCREMENT_VAL)){
            bval=true;
        }
        else if (val.equals(DECREMET_VAL)){
            bval=false;
        }
        else throw new UnsupportedOperationException();

        long commentId=Long.parseLong(text);

        LikeDto dto=upCommentService.addLikeToComment(commentId, bval);

        return new Response();
    }

    @Secured(ROLE_USER)
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public @ResponseBody
    Response getAjax(@RequestParam String text, @RequestParam String val, @RequestParam String username){

        Long id = Long.parseLong(text);
        UPArticlePost upArticlePost=upPostService.getArticlePost(id);
        UPPostLike UPPostLike =new UPPostLike();
        boolean likeexist=false;

        for (UPPostLike liken: upArticlePost.getUPPostLike()){

            if(liken.getAuthor().equals(username)){

                UPPostLike =liken;

                likeexist=true;
            }
        }
        UPPostLike.setAuthor(username);
        UPPostLike.setDate(new Date());
        UPPostLike.setUpArticlePost(upArticlePost);
        if (val.equals("dec")){
        UPPostLike.setValue(false);}
        else UPPostLike.setValue(true);
        if (likeexist){
           upLikeService.update(UPPostLike);}
        else upLikeService.save(UPPostLike);
        Response response=new Response();
        response.setRate(upPostService.getRate(id));
        response.setId(id);
        return response;



    }


    @RequestMapping(value = "/addcomment", method = RequestMethod.GET)
    public @ResponseBody CommentDto addComment(@RequestParam String article, @RequestParam String parrentlevel, @RequestParam String parrentid, @RequestParam String text){
        CommentDto commentDto=new CommentDto();
        if(!parrentid.equals("")){
            commentDto.setParent(Long.parseLong(parrentid));
        }

        commentDto.setLevel(Long.parseLong(parrentlevel)+1);
        commentDto.setTimestamp(new Date());
        commentDto.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());
        commentDto.setContent(text);
        commentDto.setPostId(Long.parseLong(article));
        long crid=upCommentService.saveNewComment(commentDto);
        commentDto.setId(crid);
        return commentDto;
    }


}
