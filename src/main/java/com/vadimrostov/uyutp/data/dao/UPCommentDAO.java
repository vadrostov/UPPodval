package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.Comment;

import java.util.List;

public interface UPCommentDAO {


    public void save(Comment comment);

    public List<Comment> getComments();

    public List<Comment> getCommentsForPost(Long id);
}
