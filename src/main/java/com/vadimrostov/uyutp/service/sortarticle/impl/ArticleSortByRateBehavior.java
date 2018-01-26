package com.vadimrostov.uyutp.service.sortarticle.impl;

import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import com.vadimrostov.uyutp.web.dto.ArticleDto;

public class ArticleSortByRateBehavior implements ArticleSortBehavior {
    public int compareTo(ArticleDto articleDto, ArticleDto articleDtoNT) {
        return (articleDto.getRate().longValue()<articleDtoNT.getRate().longValue()? -1 : (articleDto.getRate().longValue()==articleDto.getRate().longValue()?0 :1));
    }
}
