package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPComment;
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

    public void save(UPComment UPComment) {
            sessionFactory.getCurrentSession().save(UPComment);
    }

    public List<UPComment> getCommentsForPost(Long id) {
        Query query=sessionFactory.getCurrentSession().createQuery("from UPComment where upArticlePost= :post");

        query.setParameter("post", upArticlePostDAO.getArticle(id));
        List list=query.getResultList();
        return list;
    }

    public UPComment getCommentById(Long id) {
        return sessionFactory.getCurrentSession().get(UPComment.class, id);
    }

    public List<UPComment> getComments() {
        return sessionFactory.getCurrentSession().createQuery("from UPComment ").list();


    }
}
