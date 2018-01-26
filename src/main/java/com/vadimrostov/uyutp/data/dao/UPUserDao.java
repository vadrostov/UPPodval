package com.vadimrostov.uyutp.data.dao;

import com.vadimrostov.uyutp.data.domain.user.User;

public interface UPUserDao {


    public User findByEmail(String email);

    public User findByLogin(String login);

    public void saveUser(User user);

    public void updateUser(User user);
}
