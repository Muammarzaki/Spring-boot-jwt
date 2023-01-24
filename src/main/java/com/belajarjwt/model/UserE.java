package com.belajarjwt.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserE implements UserDetails {
    private String id;
    private String username;
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     * @return
     */
    public UserE setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * @param username the username to set
     * @return
     */
    public UserE setUsername(String username) {
        this.username = username;
        return this;
    }

    /**
     * @param password the password to set
     * @return
     */
    public UserE setPassword(String password) {
        this.password = password;
        return this;
    }

}
