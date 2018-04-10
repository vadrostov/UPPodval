package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPTag;
import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.web.dto.ArticleCardDto;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import org.springframework.security.access.annotation.Secured;

import java.util.Date;
import java.util.List;


public interface UPPostService {



    @Secured("ROLE_ADMIN")
    public void saveArticlePost(UPArticlePost upArticlePost);

    public UPArticlePost getArticlePost(Long id);

    public List<UPArticlePost> getArticles();

    public int getRate(Long id);

    public UPArticlePost saveNewPost(ArticleDto articleDto);

    public List<UPArticlePost> getByTag(String tagname);

    public List<ArticleDto> getListByDate(String behavior, String date);

    public List<ArticleDto> getArticleDto(String behavior, List<UPArticlePost> listPost);

    public List<ArticleDto> getAllArticleDto(String behavior);

    public List<ArticleDto> searchPost(String sortStr);

    public void update(UPArticlePost upArticlePost);

    public List<ArticleCardDto> getCards(int[] ids);

    public List<ArticleCardDto> getGardList(List<ArticleDto> dtoList);

}
