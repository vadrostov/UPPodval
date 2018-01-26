package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.user.Role;

import java.util.List;

public interface UPRoleService {

    public List<Role> getRoles();

    public Role getAdminRole();
}
