package com.vadimrostov.uyutp.data.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UP_CARD_IMAGE")
public class UPPostCardImage {

    private Long id;

    private String imgName;

    private Set<UPArticlePost> articlePosts;

    private Set<UPPostCategory> categories;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CARD_IMAGE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CARD_IMAGE_NAME")
    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "cardImage", cascade = CascadeType.ALL)
    public Set<UPArticlePost> getArticlePosts() {
        return articlePosts;
    }

    public void setArticlePosts(Set<UPArticlePost> articlePosts) {
        this.articlePosts = articlePosts;
    }

    @ManyToMany
    @JoinTable(name = "UP_CATEGORY_IMAGE", joinColumns = {@JoinColumn(name = "CARD_IMAGE_ID", nullable = false, updatable = false)}, inverseJoinColumns = {@JoinColumn(name = "CATEGORY_ID", nullable = false, updatable = false, referencedColumnName = "CATEGORY_ID")})
    public Set<UPPostCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<UPPostCategory> categories) {
        this.categories = categories;
    }
}
