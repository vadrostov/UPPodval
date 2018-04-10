package com.vadimrostov.uyutp.data.dao;


import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPTag;

import java.util.Date;
import java.util.List;


public interface UPArticlePostDAO {

    public void save(UPArticlePost upArticlePost);

    public UPArticlePost getArticle(Long id);

    public List<UPArticlePost> getArticles();

    public List<UPArticlePost> getArticlesToShow();

    public List<UPArticlePost> getArticlesByTag(UPTag tag);

    public List<UPArticlePost> getArticlesByDate(String date);

    public List<UPArticlePost> searchArticle(String str);

    public void update(UPArticlePost upArticlePost);
}
