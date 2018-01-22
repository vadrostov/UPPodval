package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPCommentDAO;
import com.vadimrostov.uyutp.data.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UPCommentServiceImpl implements UPCommentService {


    @Autowired
    UPCommentDAO upCommentDAO;

    public void saveComment(Comment comment){
        upCommentDAO.save(comment);
    }

    public List<Comment> getPostComments(Long id) {
        return upCommentDAO.getCommentsForPost(id);
    }

    public List<Comment> getComments() {
        return upCommentDAO.getComments();
    }

}
