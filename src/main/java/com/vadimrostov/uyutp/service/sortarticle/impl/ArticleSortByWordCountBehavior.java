package com.vadimrostov.uyutp.service.sortarticle.impl;

import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import com.vadimrostov.uyutp.web.dto.ArticleDto;

public class ArticleSortByWordCountBehavior implements ArticleSortBehavior {

    public int compareTo(ArticleDto articleDto, ArticleDto articleDtoNT) {
        return 0;
    }
}
