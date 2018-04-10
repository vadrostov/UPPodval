package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPPostCategory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UPPostCategoryDAOImpl implements UPPostCategoryDAO {

    @Autowired
    SessionFactory sessionFactory;

    public List<UPPostCategory> getPostCategories() {


        return sessionFactory.getCurrentSession().createQuery("from UPPostCategory ").list();
    }
}
