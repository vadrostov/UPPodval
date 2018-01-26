package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.user.Role;

import java.util.List;

public interface UPRoleDao {

    public List<Role> getRoleList();

    public Role getAdminRole();

    public Role getUserRole();
}
