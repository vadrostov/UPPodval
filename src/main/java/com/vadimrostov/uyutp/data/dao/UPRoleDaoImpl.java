package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.user.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class UPRoleDaoImpl implements UPRoleDao{

    private final static String ROLE_ADMIN="ROLE_ADMIN";
    private final static String ROLE_USER="ROLE_USER";

    @Autowired
    SessionFactory sessionFactory;

    public List<Role> getRoleList() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }

    public Role getUserRole() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Role where name=:name");
        query.setParameter("name", ROLE_USER);

        return (Role)query.getSingleResult();
    }

    public Role getAdminRole() {
        Query query=sessionFactory.getCurrentSession().createQuery("from Role where name=:name");
        query.setParameter("name", ROLE_ADMIN);

        return (Role)query.getSingleResult();
    }
}
