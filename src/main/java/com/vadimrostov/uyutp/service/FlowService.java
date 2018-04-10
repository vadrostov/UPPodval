package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import com.vadimrostov.uyutp.web.dto.TagDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Component
public class FlowService {


    @Autowired
    UPTagService tagService;

    @Autowired
    UserService userService;


    @Autowired
    UPPostService postService;

    @Autowired
    UPPostCategoryService postCategoryService;

    public String validName(String name){
        if(!name.equals(null)&&(name.length()<100)){
            return name;
        }
        else return null;
    }

    public Set<TagDto> getTags(){
        return tagService.getTags();
    }


    public void saveArticle(ArticleDto articleDto){
        articleDto.setDate(new Date());
        SimpleDateFormat format=new SimpleDateFormat("MM/dd/yyyy");
        String s= format.format(new Date());
        articleDto.setAuthor(SecurityContextHolder.getContext().getAuthentication().getName());

        articleDto.setCreated(s);
        UPArticlePost articlePost=postService.saveNewPost(articleDto);
        articleDto.setId(articlePost.getId());



    }

}
