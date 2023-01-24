package com.belajarjwt.security.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;

@Configuration
public class JWTConfig {
    @Autowired
    public RSAKeyProperties rsaKey;

    @Autowired
    public JwtToUserConverter jwtToUserConverter;

    @Bean
    @Primary
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKey.publicKey()).build();
    }

    @Bean
    @Primary
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKey.publicKey()).privateKey(rsaKey.privateKey()).build();
        ImmutableJWKSet<com.nimbusds.jose.proc.SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    @Qualifier("JwtRefrestDecoder")
    public JwtDecoder jwtRefrestDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKey.publicKey()).build();
    }

    @Bean
    @Qualifier("JwtRefrestEncoder")
    public JwtEncoder jwtRefrestEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKey.publicKey()).privateKey(rsaKey.privateKey()).build();
        ImmutableJWKSet<com.nimbusds.jose.proc.SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    @Qualifier("JwtRefrestAuthenticationProvider")
    public JwtAuthenticationProvider jwtRefrestAuthenticationProvider() {
        JwtAuthenticationProvider jwtAuthProvider = new JwtAuthenticationProvider(jwtRefrestDecoder());
        jwtAuthProvider.setJwtAuthenticationConverter(jwtToUserConverter);
        return jwtAuthProvider;
    }

    /**
     * @param jwtToUserConverter the jwtToUserConverter to set
     */
    public void setJwtToUserConverter(JwtToUserConverter jwtToUserConverter) {
        this.jwtToUserConverter = jwtToUserConverter;
    }
}
