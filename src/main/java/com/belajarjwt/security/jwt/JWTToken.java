package com.belajarjwt.security.jwt;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Component;

import com.belajarjwt.dto.JwtDto;
import com.belajarjwt.model.UserE;

@Component
public class JWTToken {

    @Autowired
    JwtEncoder jwtAccessEncoder;

    @Autowired
    @Qualifier("JwtRefrestEncoder")
    JwtEncoder jwtRefrestEncoder;

    private String accessTokenGenerator(Authentication authentication) {
        UserE user = (UserE) authentication.getPrincipal();
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.MINUTES))
                .subject(user.getId())
                .build();

        return this.jwtAccessEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    private String refrestTokenGenerator(Authentication authentication) {
        UserE user = (UserE) authentication.getPrincipal();
        Instant now = Instant.now();
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.DAYS))
                .subject(user.getId())
                .build();

        return this.jwtRefrestEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }

    public JwtDto tokenGenerator(Authentication authentication) {
        if (!(authentication.getPrincipal() instanceof UserE userE)) {
            throw new BadCredentialsException(
                    MessageFormat.format("principal {0} is not of User type",
                            authentication.getPrincipal().getClass()));
        }

        JwtDto jwtDto = new JwtDto();
        jwtDto.setId(userE.getId());
        jwtDto.setAccessToken(accessTokenGenerator(authentication));
        String refreshToken;
        if ((authentication.getCredentials() instanceof Jwt jwt)) {
            Instant now = Instant.now();
            Instant expiresAt = jwt.getExpiresAt();
            Duration duration = Duration.between(now, expiresAt);
            long daysUntilExpired = duration.toHours();
            if (daysUntilExpired < 3) {
                refreshToken = refrestTokenGenerator(authentication);
            } else {
                refreshToken = jwt.getTokenValue();
            }
        } else {
            refreshToken = refrestTokenGenerator(authentication);
        }
        jwtDto.setRefrestToken(refreshToken);
        return jwtDto;
    }

}
