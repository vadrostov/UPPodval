package com.vadimrostov.uyutp.data.dao;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;

import java.util.List;


public interface UPArticlePostDAO {

    public void save(UPArticlePost upArticlePost);

    public UPArticlePost getArticle(Long id);

    public List<UPArticlePost> getArticles();
}
