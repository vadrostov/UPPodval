package com.vadimrostov.uyutp.data.domain;


import javax.persistence.*;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "UP_ARTICLE_LIKE")
public class Like {


    private Long id;


    private UPArticlePost upArticlePost;

    private Date date;

    private boolean value;

    private String author;

    @Id
    @Column(name = "LIKE_ID")
    @GeneratedValue(strategy = IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "ARTICLE_ID")
    public UPArticlePost getUpArticlePost() {
        return upArticlePost;
    }

    public void setUpArticlePost(UPArticlePost upArticlePost) {
        this.upArticlePost = upArticlePost;
    }

    @Column(name = "LIKE_DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "LIKE_VALUE")
    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @Column(name = "LIKE_AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
