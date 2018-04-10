package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import com.vadimrostov.uyutp.data.domain.UPTag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.Query;
import java.util.Date;
import java.util.List;


@Repository
public class UPArticlePostDAOImpl implements UPArticlePostDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<UPArticlePost> getArticlesToShow() {
        Query query=sessionFactory.getCurrentSession().createQuery("from UPArticlePost where deleted=:deleted");
        query.setParameter("deleted", false);
        return query.getResultList();
    }


    public List<UPArticlePost> getArticlesByDate(String date) {
        Query query=sessionFactory.getCurrentSession().createQuery("from UPArticlePost where creationDate=:date");
        query.setParameter("date", date);

        return query.getResultList();
    }

    public List<UPArticlePost> getArticlesByTag(UPTag tag) {
        Query query=sessionFactory.getCurrentSession().createQuery("select a from UPArticlePost a join a.tags t where t.id=:tid ");
        query.setParameter("tid", tag.getId());
        return query.getResultList();
    }

    public void save(UPArticlePost upArticlePost){
        sessionFactory.getCurrentSession().save(upArticlePost);
    }



    public UPArticlePost getArticle(Long id) {
        return sessionFactory.getCurrentSession().get(UPArticlePost.class, id);
    }


    public List<UPArticlePost> getArticles(){

        return sessionFactory.getCurrentSession().createQuery("from UPArticlePost ").list();
    }

    public List<UPArticlePost> searchArticle(String str) {
        return null;
    }

    public void update(UPArticlePost upArticlePost) {
        sessionFactory.getCurrentSession().update(upArticlePost);
    }
}
