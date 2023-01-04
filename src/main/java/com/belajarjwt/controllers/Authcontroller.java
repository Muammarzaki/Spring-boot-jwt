package com.belajarjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajarjwt.security.services.JWTToken;

@RestController
@RequestMapping("/api")
public class Authcontroller {
    private JWTToken jwtToken;

    @GetMapping("/token")
    public String token(Authentication authentication) {
        return jwtToken.generateToken(authentication);
    }

    /**
     * @param jwtToken the jwtToken to set
     */
    @Autowired(required = true)
    public void setJwtToken(JWTToken jwtToken) {
        this.jwtToken = jwtToken;
    }
}
