package com.vadimrostov.uyutp.data.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UP_POST_CATEGORY")
public class UPPostCategory {

    private Long id;

    private String categoryName;

    private Set<UPArticlePost> posts;

    private Set<UPPostCardImage> images;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CATEGORY_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "CATEGORY_NAME")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;

    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    public Set<UPArticlePost> getPosts() {
        return posts;
    }

    public void setPosts(Set<UPArticlePost> posts) {
        this.posts = posts;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "categories")
    public Set<UPPostCardImage> getImages() {
        return images;
    }

    public void setImages(Set<UPPostCardImage> images) {
        this.images = images;
    }
}
