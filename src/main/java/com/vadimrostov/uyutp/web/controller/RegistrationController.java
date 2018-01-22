package com.vadimrostov.uyutp.web.controller;

import com.vadimrostov.uyutp.data.domain.user.User;
import com.vadimrostov.uyutp.security.validation.EmailExistsException;
import com.vadimrostov.uyutp.security.validation.LoginExistException;
import com.vadimrostov.uyutp.service.UserService;
import com.vadimrostov.uyutp.web.controller.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class RegistrationController {


    @Autowired
    ModelAndView modelAndView;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/registration", method = RequestMethod.GET)
    public ModelAndView showRegForm(){
        UserDto userDto=new UserDto();
        modelAndView.addObject("user", userDto);
        modelAndView.setViewName("userRegistration");
        return modelAndView;
    }

    @RequestMapping(value = "/user/registration", method = RequestMethod.POST)
    public ModelAndView registerUser(@Valid UserDto accDto, Errors errors){
        User user=null;
        if (errors.hasErrors()){
            modelAndView.setViewName("userRegistration");
            return modelAndView;
        }

        user=createUserAccount(accDto);
        if (user==null){
            modelAndView.setViewName("userRegistration");
            return modelAndView;
        }

        modelAndView.setViewName("redirect:/user/"+user.getLogin());
        return modelAndView;


    }

    private User createUserAccount(UserDto accountDto) {
        User registered = null;
        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (EmailExistsException e) {
            return null;
        }
        catch (LoginExistException e){
            return null;
        }
        return registered;
    }
}
