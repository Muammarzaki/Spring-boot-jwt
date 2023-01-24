package com.belajarjwt.security.jwt;

import java.util.Collections;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import com.belajarjwt.model.UserE;

/**
 * JwAuthenticationtConverter
 */
@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt arg0) {
        UserE user = new UserE();
        user.setId(arg0.getSubject());
        return new UsernamePasswordAuthenticationToken(user, arg0, Collections.emptyList());
    }

}