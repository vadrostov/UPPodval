package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.Comment;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UpCommentDAOImpl implements UPCommentDAO {

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    UPArticlePostDAO upArticlePostDAO;

    public void save(Comment comment) {
            sessionFactory.getCurrentSession().save(comment);
    }

    public List<Comment> getCommentsForPost(Long id) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Comment where upArticlePost= :post");

        query.setParameter("post", upArticlePostDAO.getArticle(id));
        List list=query.getResultList();
        return list;
    }

    public List<Comment> getComments() {
        return sessionFactory.getCurrentSession().createQuery("from Comment ").list();


    }
}
