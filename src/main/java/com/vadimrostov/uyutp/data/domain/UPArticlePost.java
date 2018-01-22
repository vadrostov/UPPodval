package com.vadimrostov.uyutp.data.domain;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UP_ARTICLE")
public class UPArticlePost {


    private Long id;

    private String name;

    private String description;

    private String content;


    private Date date;

    private String author;

    private Set<Like> like;

    private Set<Comment> comments;

    @Id
    @Column(name = "ARTICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "ARTICLE_NAME", length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "ARTICLE_DESC")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ARTICLE_AUTHOR")
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Column(name = "ARTICLE_CONTENT" ,columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column(name="ARTICLE_DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upArticlePost", cascade = CascadeType.ALL)
    public Set<Like> getLike() {
        return like;
    }

    public void setLike(Set<Like> like) {
        this.like = like;
    }


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upArticlePost", cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    public Long getRate(){
        long count=0;
        for (Like like: like){
            if (like.isValue()){
                count++;
            }
            else count--;
        }
        return count;
    }

    public void setRate(Long rate){

    }
}
