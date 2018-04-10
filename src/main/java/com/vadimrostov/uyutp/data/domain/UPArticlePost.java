package com.vadimrostov.uyutp.data.domain;


import com.vadimrostov.uyutp.data.domain.user.User;

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

    private String creationDate;

    private User user;

    private boolean deleted;

    private Date date;

    private String author;

    private UPPostCardImage cardImage;

    private Set<UPPostLike> UPPostLike;

    private Set<UPComment> UPComments;

    private Set<UPTag> tags;

    private Set<UPPostCategory> categories;

    @Id
    @Column(name = "ARTICLE_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "CARD_IMAGE_ID")
    public UPPostCardImage getCardImage() {
        return cardImage;
    }

    public void setCardImage(UPPostCardImage cardImage) {
        this.cardImage = cardImage;
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
    @Column(name = "ARTICLE_CRATED")
    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
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

    @Column(name = "ARTICLE_DEL")
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upArticlePost", cascade = CascadeType.ALL)
    public Set<UPPostLike> getUPPostLike() {
        return UPPostLike;
    }

    public void setUPPostLike(Set<UPPostLike> UPPostLike) {
        this.UPPostLike = UPPostLike;
    }


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UP_ARTICLE_TAG", joinColumns = {@JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "TAG_ID", nullable = false, updatable = false, referencedColumnName = "TAG_ID")})
    public Set<UPTag> getTags() {
        return tags;
    }

    public void setTags(Set<UPTag> tags) {
        this.tags = tags;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "upArticlePost", cascade = CascadeType.ALL)
    public Set<UPComment> getUPComments() {
        return UPComments;
    }

    public void setUPComments(Set<UPComment> UPComments) {
        this.UPComments = UPComments;
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
        for (UPPostLike UPPostLike : this.UPPostLike){
            if (UPPostLike.isValue()){
                count++;
            }
            else count--;
        }
        return count;
    }

    public void setRate(Long rate){

    }

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "UP_ARTICLE_CATEGORY", joinColumns = {@JoinColumn(name = "ARTICLE_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false, referencedColumnName = "CATEGORY_ID")})
    public Set<UPPostCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<UPPostCategory> categories) {
        this.categories = categories;
    }
}
