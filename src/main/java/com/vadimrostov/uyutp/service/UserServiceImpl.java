package com.vadimrostov.uyutp.service;

import com.vadimrostov.uyutp.data.dao.UPRoleDao;
import com.vadimrostov.uyutp.data.dao.UPUserDao;
import com.vadimrostov.uyutp.data.domain.user.Role;
import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.security.validation.EmailExistsException;
import com.vadimrostov.uyutp.security.validation.LoginExistException;
import com.vadimrostov.uyutp.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UPUserDao upUserDao;

    @Autowired
    private UPRoleDao upRoleDao;

    public User getByLogin(String login) {
        return upUserDao.findByLogin(login);
    }

    public User registerNewUserAccount(UserDto userDto) throws EmailExistsException, LoginExistException{
        if (emailExist(userDto.getEmail())){
            throw new EmailExistsException("Емэйл существует"+userDto.getEmail());
        }
        if (loginExist(userDto.getLogin())){
            throw new LoginExistException("Пользователь с логином "+userDto.getLogin()+" уже существует!");
        }

        User user=new User();
        user.setEmail(userDto.getEmail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());
        Set<Role> roleSet=new HashSet<Role>();
        roleSet.add(upRoleDao.getUserRole());
        user.setRoles(roleSet);

        upUserDao.saveUser(user);
        return user;

    }

    public boolean loginExist(String login){
        User user=upUserDao.findByLogin(login);
        if(user!=null){
            return true;
        }
        return false;
    }

    public boolean emailExist(String email){
      User user= upUserDao.findByEmail(email);
       if(user!=null){
           return true;
       }
       return false;
    }

    public void updateUser(User user) {
        upUserDao.updateUser(user);
    }

    public void saveUser(User user) {
        upUserDao.saveUser(user);
    }

    public UserDto getUserDto(User user) {
        UserDto userDto=new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        return userDto;
    }
}
