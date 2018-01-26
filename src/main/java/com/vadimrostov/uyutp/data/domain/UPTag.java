package com.vadimrostov.uyutp.data.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UP_TAG")
public class UPTag {


    private Long id;

    private String name;

    private Set<UPArticlePost> upArticlePosts;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "TAG_NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "tags")
    public Set<UPArticlePost> getUpArticlePosts() {
        return upArticlePosts;
    }

    public void setUpArticlePosts(Set<UPArticlePost> upArticlePosts) {
        this.upArticlePosts = upArticlePosts;
    }
}
