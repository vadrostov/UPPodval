package com.vadimrostov.uyutp.data.domain;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UP_COMMENT")
public class Comment {

    private Long id;

    private UPArticlePost upArticlePost;

    private String author;

    private Date timestamp;

    private String content;

    @Id
    @Column(name = "COMMENT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Column(name = "COMMENT_AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "COMMENT_DATE")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Column(name = "COMMENT_COMTENT")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
