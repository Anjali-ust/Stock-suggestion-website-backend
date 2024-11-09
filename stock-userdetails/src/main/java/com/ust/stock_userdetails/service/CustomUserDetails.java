package com.ust.stock_userdetails.service;

import java.util.Collection;
import java.util.List;

import com.ust.stock_userdetails.Model.UserCredentials;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private String username;
    private String password;
    private List<SimpleGrantedAuthority> allRoles;

    public CustomUserDetails(UserCredentials user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        //this.allRoles = allRoles.stream().map((role)-> new SimpleGrantedAuthority(user.getRole())).toList();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return this.allRoles;
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}