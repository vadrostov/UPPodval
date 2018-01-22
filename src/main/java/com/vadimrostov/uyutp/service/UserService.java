package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.security.validation.EmailExistsException;
import com.vadimrostov.uyutp.security.validation.LoginExistException;
import com.vadimrostov.uyutp.web.controller.dto.UserDto;

public interface UserService {


    public User registerNewUserAccount(UserDto userDto)throws EmailExistsException, LoginExistException;

    public User getByLogin(String login);
}
