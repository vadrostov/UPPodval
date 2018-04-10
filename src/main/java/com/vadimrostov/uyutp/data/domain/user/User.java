package com.vadimrostov.uyutp.data.domain.user;

import com.vadimrostov.uyutp.data.domain.UPComment;
import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPCommentLike;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "UP_USERS")
public class User {

    private Long id;

    private String email;

    private String login;

    private String password;

    private boolean enabled;

    private Set<Role> roles;

    private Set<UPArticlePost> posts;

    private Set<UPComment> UPComments;

    private Set<UPCommentLike> commentLikes;




    @Id
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }



    @Column(name = "USER_EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "USER_LOGIN")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "USER_PASSWORD")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }



    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "USER_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UPArticlePost> getPosts() {
        return posts;
    }

    public void setPosts(Set<UPArticlePost> posts) {
        this.posts = posts;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UPCommentLike> getCommentLikes() {
        return commentLikes;
    }

    public void setCommentLikes(Set<UPCommentLike> commentLikes) {
        this.commentLikes = commentLikes;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.ALL)
    public Set<UPComment> getUPComments() {
        return UPComments;
    }

    public void setUPComments(Set<UPComment> UPComments) {
        this.UPComments = UPComments;
    }
}
