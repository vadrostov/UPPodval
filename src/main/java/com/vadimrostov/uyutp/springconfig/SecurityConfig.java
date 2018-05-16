package com.vadimrostov.uyutp.springconfig;

import com.vadimrostov.uyutp.security.accesshandlers.AjaxAuthEntryPoint;
import com.vadimrostov.uyutp.security.accesshandlers.AjaxAuthFailureHandler;
import com.vadimrostov.uyutp.security.accesshandlers.AjaxAuthSuccessHandler;
import com.vadimrostov.uyutp.security.service.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.util.Arrays;

@Configuration
@EnableWebMvcSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    @Qualifier("myUserDetailService")
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    AjaxAuthSuccessHandler ajaxAuthSuccessHandler;

    @Autowired
    AjaxAuthEntryPoint ajaxAuthEntryPoint;

    @Autowired
    AjaxAuthFailureHandler ajaxAuthFailureHandler;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login*").permitAll()
                .and()
                .
                csrf().disable();
        http.formLogin().loginProcessingUrl("/j_spring_security_check");
        http.formLogin().usernameParameter("j_username");
        http.formLogin().passwordParameter("j_password");
        http.exceptionHandling().authenticationEntryPoint(ajaxAuthEntryPoint);
        http.formLogin().successHandler(ajaxAuthSuccessHandler);
        http.formLogin().failureHandler(ajaxAuthFailureHandler);

    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
