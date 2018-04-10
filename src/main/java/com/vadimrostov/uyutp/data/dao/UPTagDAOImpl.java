package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.UPTag;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;


@Repository
public class UPTagDAOImpl implements UPTagDao{

    @Autowired
    SessionFactory sessionFactory;

    public void saveTag(UPTag tag) {
        sessionFactory.getCurrentSession().save(tag);


    }

    public UPTag getTagByName(String name) {
        try {
            Query query=sessionFactory.getCurrentSession().createQuery("from UPTag where name=:name");
            query.setParameter("name", name);


            return (UPTag) query.getSingleResult();
        }
        catch (Exception e){
            return null;
        }

    }

    public List<UPTag> getTags() {
        return sessionFactory.getCurrentSession().createQuery("from UPTag ").list();
    }
}
