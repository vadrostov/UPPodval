package com.vadimrostov.uyutp.security.service;

import com.vadimrostov.uyutp.data.dao.UPUserDao;
import com.vadimrostov.uyutp.data.domain.user.Role;
import com.vadimrostov.uyutp.data.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class MyUserDetailService implements UserDetailsService{

    @Autowired
    UPUserDao upUserDao;


    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try{
            final User user=upUserDao.findByLogin(login);
            if (user==null){
                throw new UsernameNotFoundException("No user found with login:"+login);
            }
            return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), user.isEnabled(), true, true, true, getAuthorities(user.getRoles()));
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }
    }


    private final Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles){
        final List<GrantedAuthority> grantedAuthorities=new ArrayList<GrantedAuthority>();
        for (Role role:roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return grantedAuthorities;
    }
}
