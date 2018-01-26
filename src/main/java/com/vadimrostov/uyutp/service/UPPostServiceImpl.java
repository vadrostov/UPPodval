package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.dao.UPArticlePostDAO;
import com.vadimrostov.uyutp.data.dao.UPTagDao;
import com.vadimrostov.uyutp.data.domain.Comment;
import com.vadimrostov.uyutp.data.domain.Like;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPTag;
import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByDateBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByHotBehavior;
import com.vadimrostov.uyutp.service.sortarticle.impl.ArticleSortByRateBehavior;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UPPostServiceImpl implements UPPostService{


    @Autowired
    UPArticlePostDAO upArticlePostDAO;


    @Autowired
    UPTagService upTagService;


    public UPArticlePost saveNewPost(ArticleDto articleDto) {
        UPArticlePost upArticlePost = new UPArticlePost();
        upArticlePost.setLike(new HashSet<Like>());
        upArticlePost.setAuthor(articleDto.getAuthor());
        upArticlePost.setContent(articleDto.getContent());
        upArticlePost.setDate(articleDto.getDate());
        upArticlePost.setComments(new HashSet<Comment>());
        upArticlePost.setName(articleDto.getName());
        upArticlePost.setRate(new Long(0));
        upArticlePost.setTags(getTagList(articleDto.getTags()));

        try{
        upArticlePostDAO.save(upArticlePost);
        return upArticlePost;}
        catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


    public List<ArticleDto> getArticleDto(String behavior, List<UPArticlePost> listPost) {
        List<ArticleDto> dtoList=new ArrayList<ArticleDto>();
        for (UPArticlePost upArticlePost: listPost){
            ArticleSortBehavior articleSortBehavior;
            if (behavior.equals("rate")) articleSortBehavior=new ArticleSortByRateBehavior();
            else if (behavior.equals("date")) articleSortBehavior= new ArticleSortByDateBehavior();
            else articleSortBehavior=new ArticleSortByHotBehavior();

            ArticleDto dto=new ArticleDto(articleSortBehavior);
            dto.setAuthor(upArticlePost.getAuthor());
            dto.setDate(upArticlePost.getDate());
            dto.setDescription(upArticlePost.getDescription());
            dto.setName(upArticlePost.getName());



        }
        return null;
    }

    public List<UPArticlePost> getByTag(String tagname) {
        UPTag tag= upTagService.getByName(tagname);
        if (tag!=null){
            return upArticlePostDAO.getArticlesByTag(tag);
        }
        else         return null;
    }

    public Set<UPTag> getTagList(String tagStr){
        String[] tagArray=tagStr.split(",");
        Set<UPTag>tagSet= new HashSet<UPTag>();

        for (String tagString: tagArray){
            tagString=tagString.trim();
            UPTag tag=upTagService.getOrCreateTag(tagString);
            tagSet.add(tag);
        }

        return tagSet;


    }

    @Secured("ROLE_ADMIN")
    public void saveArticlePost(UPArticlePost upArticlePost){

        upArticlePostDAO.save(upArticlePost);

    }

    public UPArticlePost getArticlePost(Long id) {
        return upArticlePostDAO.getArticle(id);

    }

    public int getRate(Long id) {
        UPArticlePost upArticlePost=upArticlePostDAO.getArticle(id);
        int i=0;
        for (Like like: upArticlePost.getLike()){
            if(like.isValue()){
                i++;
            }
            else i--;
        }
        return i;
    }

    public List<UPArticlePost> getArticles() {
        return upArticlePostDAO.getArticles();
    }
}
