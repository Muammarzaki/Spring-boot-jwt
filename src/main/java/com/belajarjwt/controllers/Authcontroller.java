package com.belajarjwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.belajarjwt.dto.JwtDto;
import com.belajarjwt.dto.LoginDto;
import com.belajarjwt.security.jwt.JWTToken;

/**
 * AuthController
 */
@RestController
@RequestMapping("api/auth")
public class AuthController {
    @Autowired
    public AuthenticationManager authenticationManager;

    @Autowired
    private JWTToken jwtToken;

    @Autowired
    @Qualifier("JwtRefrestAuthenticationProvider")
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @PostMapping("login")
    public JwtDto login(@RequestBody LoginDto loginDto) {
        System.out.println(loginDto.getUsername());
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        return jwtToken.tokenGenerator(authenticate);
    }

    @PostMapping("register")
    public void register() {
        // TODO buat register
    }

    @PostMapping("token")
    public JwtDto refestToken(@RequestBody JwtDto jwtDto) {
        Authentication authenticate = jwtAuthenticationProvider
                .authenticate(new BearerTokenAuthenticationToken(jwtDto.getRefrestToken()));

        System.out.println(authenticate.isAuthenticated());
        return jwtToken.tokenGenerator(authenticate);
    }

}