package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPRoleDao;
import com.vadimrostov.uyutp.data.domain.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UPRoleServiceImpl implements UPRoleService {

    @Autowired
    UPRoleDao upRoleDao;

    public Role getAdminRole() {
        return upRoleDao.getAdminRole();
    }

    public List<Role> getRoles() {
        return upRoleDao.getRoleList();
    }
}
