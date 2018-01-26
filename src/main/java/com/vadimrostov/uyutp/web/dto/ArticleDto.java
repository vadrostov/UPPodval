package com.vadimrostov.uyutp.web.dto;

import com.vadimrostov.uyutp.service.sortarticle.ArticleSortBehavior;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ArticleDto implements Comparable{



    private ArticleSortBehavior sortBehavior;



    @NotNull
    private String name;


    private String description;

    @NotNull
    private String content;

    private String tags;

    private String author;

    private Date date;

    private Long rate;

    public String getName() {
        return name;
    }

    public ArticleDto() {
    }

    public ArticleDto(ArticleSortBehavior sortBehavior) {
        this.sortBehavior = sortBehavior;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
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

    public Long getRate() {
        return rate;
    }

    public void setRate(Long rate) {
        this.rate = rate;
    }

    public int compareTo(Object o) {
        if(sortBehavior==null){
        return 0;}
        else return sortBehavior.compareTo(this, (ArticleDto)o);
    }
}
