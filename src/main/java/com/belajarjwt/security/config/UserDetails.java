package com.belajarjwt.security.config;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.belajarjwt.model.UserE;

/**
 * UserDetails
 */
@Service
public class UserDetails implements UserDetailsService {

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new UserE().setId("id").setUsername("foo").setPassword("{noop}foo");
    }

}