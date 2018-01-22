package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.Like;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UPLikeDAOImpl implements UPLikeDAO {

    @Autowired
    SessionFactory sessionFactory;


    public void save(Like like) {
        sessionFactory.getCurrentSession().save(like);

    }

    public void delete(Like like) {
        sessionFactory.getCurrentSession().delete(like);
    }

    public void update(Like like) {
        sessionFactory.getCurrentSession().update(like);
    }
}
