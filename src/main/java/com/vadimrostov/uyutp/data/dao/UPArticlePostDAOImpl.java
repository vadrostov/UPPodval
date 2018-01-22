package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPArticlePost;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class UPArticlePostDAOImpl implements UPArticlePostDAO {

    @Autowired
    SessionFactory sessionFactory;

    public void save(UPArticlePost upArticlePost){
        sessionFactory.getCurrentSession().save(upArticlePost);
    }



    public UPArticlePost getArticle(Long id) {
        return sessionFactory.getCurrentSession().get(UPArticlePost.class, id);
    }


    public List<UPArticlePost> getArticles(){
        return sessionFactory.getCurrentSession().createQuery("from UPArticlePost ").list();
    }
}
