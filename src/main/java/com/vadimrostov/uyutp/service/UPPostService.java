package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;

import java.util.List;


public interface UPPostService {



    public void saveArticlePost(UPArticlePost upArticlePost);

    public UPArticlePost getArticlePost(Long id);

    public List<UPArticlePost> getArticles();

    public int getRate(Long id);
}
