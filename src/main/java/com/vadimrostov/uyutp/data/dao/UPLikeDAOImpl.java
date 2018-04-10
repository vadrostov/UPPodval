package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPPostLike;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UPLikeDAOImpl implements UPLikeDAO {

    @Autowired
    SessionFactory sessionFactory;


    public void save(UPPostLike UPPostLike) {
        sessionFactory.getCurrentSession().save(UPPostLike);

    }

    public void delete(UPPostLike UPPostLike) {
        sessionFactory.getCurrentSession().delete(UPPostLike);
    }

    public void update(UPPostLike UPPostLike) {
        sessionFactory.getCurrentSession().update(UPPostLike);
    }
}
