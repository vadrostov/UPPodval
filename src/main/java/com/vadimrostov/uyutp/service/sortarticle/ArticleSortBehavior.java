package com.vadimrostov.uyutp.service.sortarticle;

import com.vadimrostov.uyutp.web.dto.ArticleDto;

public interface ArticleSortBehavior {


    public int compareTo(ArticleDto articleDto, ArticleDto articleDtoNT);
}
