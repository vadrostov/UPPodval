package com.vadimrostov.uyutp.data.domain;


import com.vadimrostov.uyutp.data.domain.user.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "UP_COMMENT_LIKE")
public class UPCommentLike {

    private Long id;

    private UPComment UPComment;

    private Date date;

    private boolean value;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="COMMENTLIKE_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "COMMENT_ID")
    public UPComment getUPComment() {
        return UPComment;
    }

    public void setUPComment(com.vadimrostov.uyutp.data.domain.UPComment UPComment) {
        this.UPComment = UPComment;
    }

    @Column(name="COMMENTLIKE_DATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="COMMENTLIKE_VALUE")
    public boolean isValue() {
        return value;
    }

    public void setValue(boolean value) {
        this.value = value;
    }

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
