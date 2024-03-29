package com.chillmo.developer.tournamentApp.security.config;

import com.chillmo.developer.tournamentApp.security.PasswordEncoder;
import com.chillmo.developer.tournamentApp.user.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
private  UserService userService;

private PasswordEncoder passwordEncoder;

public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder){
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
}
    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/**")
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .formLogin();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)throws Exception{
        auth.authenticationProvider(daoAuthenticationProvider());
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider =
                new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder.bCryptPasswordEncoder());
        provider.setUserDetailsService(userService);
        return provider;
    }

    public void setUserService(UserService userService){
        this.userService = userService;
    }
}
