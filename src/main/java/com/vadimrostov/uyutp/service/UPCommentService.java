package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.Comment;

import java.util.List;

public interface UPCommentService {

    public void saveComment(Comment comment);

    public List<Comment> getComments();

    public List<Comment> getPostComments(Long id);
}
