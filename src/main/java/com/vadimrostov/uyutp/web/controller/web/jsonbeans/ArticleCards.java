package com.vadimrostov.uyutp.web.controller.web.jsonbeans;

import com.vadimrostov.uyutp.web.dto.ArticleCardDto;

import java.util.List;

public class ArticleCards {

    List<ArticleCardDto> cardDtoList;

    public ArticleCards(List<ArticleCardDto> cardDtoList) {
        this.cardDtoList = cardDtoList;
    }


    public List<ArticleCardDto> getCardDtoList() {
        return cardDtoList;
    }

    public void setCardDtoList(List<ArticleCardDto> cardDtoList) {
        this.cardDtoList = cardDtoList;
    }
}
