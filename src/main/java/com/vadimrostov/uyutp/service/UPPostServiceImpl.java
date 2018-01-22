package com.vadimrostov.uyutp.service;


import com.vadimrostov.uyutp.data.dao.UPArticlePostDAO;
import com.vadimrostov.uyutp.data.domain.Like;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UPPostServiceImpl implements UPPostService{


    @Autowired
    UPArticlePostDAO upArticlePostDAO;

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
