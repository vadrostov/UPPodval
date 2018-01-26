package com.vadimrostov.uyutp.web.controller;

import com.vadimrostov.uyutp.data.domain.user.Role;
import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.service.UPRoleService;
import com.vadimrostov.uyutp.service.UserService;
import com.vadimrostov.uyutp.web.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class UPUserController {

    @Autowired
    ModelAndView modelAndView;

    @Autowired
    UserService userService;

    @Autowired
    UPRoleService upRoleService;

    @RequestMapping(value = "/user/{userlogin}")
    public ModelAndView showUserInfo(@PathVariable String userlogin){
        User user=userService.getByLogin(userlogin);
        UserDto userDto=userService.getUserDto(user);
        modelAndView.addObject("user", userDto);
        modelAndView.addObject("roles", upRoleService.getRoles());
        modelAndView.setViewName("showUser");
        return modelAndView;

    }

    @RequestMapping(value = "/user/submit", method = RequestMethod.POST)
    public ModelAndView userSubmit(UserDto userDto){
        User user=userService.getByLogin(userDto.getLogin());

        if (userDto.isMakeadmin()){
            Set<Role> userRoles =user.getRoles();
            userRoles.add(upRoleService.getAdminRole());
            user.setRoles(userRoles);
            userService.updateUser(user);
            }
            modelAndView.setViewName("redirect:/user/"+user.getLogin());
        return modelAndView;

    }





}
