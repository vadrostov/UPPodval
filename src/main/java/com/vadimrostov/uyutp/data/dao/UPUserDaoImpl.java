package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.user.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.Query;


@Repository
public class UPUserDaoImpl implements UPUserDao {

    @Autowired
    SessionFactory sessionFactory;

    public User findByEmail(String email) {
        Query query=sessionFactory.getCurrentSession().createQuery("from User where email=:email");
        query.setParameter("email", email);

        User user=null;
        try {
            user = (User) query.getSingleResult();

        }
        catch (NoResultException e){

        }
        return user;
    }

    public void saveUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    public User findByLogin(String login) {
        Query query=sessionFactory.getCurrentSession().createQuery("from User where login=:login");
        query.setParameter("login", login);
        User user=null;
        try {
            user = (User) query.getSingleResult();

        }
        catch (NoResultException e){

        }
        return user;
    }
}
