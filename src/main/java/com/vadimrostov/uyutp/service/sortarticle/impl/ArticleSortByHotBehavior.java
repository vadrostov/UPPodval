package com.vadimrostov.uyutp.service.sortarticle.impl;

import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;
import com.vadimrostov.uyutp.web.dto.ArticleDto;
import com.vadimrostov.uyutp.web.dto.CommentDto;

import java.util.Date;

public class ArticleSortByHotBehavior implements ArticleSortBehavior {
    public int compareTo(ArticleDto articleDto, ArticleDto articleDtoNT) {


        long dtoHotRate=commentCountForTime(articleDto)*2+articleDto.getRate();
        long dtoNTHotRate=commentCountForTime(articleDtoNT)*2+articleDtoNT .getRate();
        return dtoHotRate<dtoNTHotRate?-1:(dtoHotRate==dtoNTHotRate)?0:1;
    }

    public int commentCountForTime(ArticleDto articleDto){
        int dtoComForTime=0;
        for (CommentDto commentDto: articleDto.getCommentDtoList()){
            Date date=new Date();
            long curTime=date.getTime();
            if (commentDto.getTimestamp().getTime()>curTime-21600000){
                dtoComForTime++;
            }
        }
        return dtoComForTime;
    }
}
