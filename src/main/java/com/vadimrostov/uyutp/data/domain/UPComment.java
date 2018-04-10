package com.vadimrostov.uyutp.data.domain;


import com.vadimrostov.uyutp.data.domain.user.User;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "UP_COMMENT")
public class UPComment {

    private Long id;

    private UPArticlePost upArticlePost;

    private String author;

    private Date timestamp;

    private String content;

    private Long parrentComment;

    private Long level;

    private User user;

    private Set<UPCommentLike> commentLikes;

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

    @Column(name = "PARRENT_ID")
    public Long getParrentComment() {
        return parrentComment;
    }

    public void setParrentComment(Long parrentComment) {
        this.parrentComment = parrentComment;
    }

    @Column(name = "COMMENT_LEVEL")
    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "UPComment", cascade = CascadeType.ALL)
    public Set<UPCommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<UPCommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public Long getRate(){
        long count=0;
        for (UPCommentLike upCommentLike : this.commentLikes){
            if (upCommentLike.isValue()){
                count++;
            }
            else count--;
        }
        return count;
    }

    public void setRate(Long rate){

    }
}
