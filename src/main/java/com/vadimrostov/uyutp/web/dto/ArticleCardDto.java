package com.vadimrostov.uyutp.web.dto;

import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;

import java.util.Date;
import java.util.List;

public class ArticleCardDto{

    private Long id;

    private String name;

    private String content;

    private String author;

    private Date date;

    public ArticleCardDto(Long id, String name, String content, String author, Date date) {
        this.id = id;
        this.name = name;
        this.content = content;
        this.author = author;
        this.date=date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
