package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPComment;

import java.util.List;

public interface UPCommentDAO {


    public void save(UPComment UPComment);

    public List<UPComment> getComments();

    public List<UPComment> getCommentsForPost(Long id);

    public UPComment getCommentById(Long id);
}
