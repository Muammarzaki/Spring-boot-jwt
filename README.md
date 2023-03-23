# Spring-boot-jwt

simple implementing json web token in spring security with dependensi **spring-security-oauth2-resource-server** However, the support for decoding and verifying JWTs is in **spring-security-oauth2-jose**

in this project using **ASYMMETRIC ENCRYPTION** with RSA encryption.

This project is only for learning and improving my skills.

## Jwt Decoder

**Jwt Decoder configuration** membutuhkan Public RSAKey yang akan digunakan untuk *Verify* token

```java
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey(rsaKey.publicKey()).build();
    }
```

## Jwt Encoder

**Jwt Encoder configuration** membutuhkan public dan private RSAKey ini akan digunakan sebagai acuan pembuatan token Dan akan sesuai jika di decode

```java
    @Bean
    public JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder(rsaKey.publicKey()).privateKey(rsaKey.privateKey()).build();
        ImmutableJWKSet<com.nimbusds.jose.proc.SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }
```

## Spring Security Configuration

Untuk mengaktifkan fitur jwt di Spring Security perlu menambahkan sebagai berikut

```java
 
    private JwtToUserConverter jwtToUserConverter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        ...
        http.oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtToUserConverter)));

        http.exceptionHandling(
                exception -> exception
                        .authenticationEntryPoint(new BearerTokenAuthenticationEntryPoint())
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));

        ...
        return http.build();
    }
```

versi simple nya panggil saja method reference dari jwt() tanpa configurasi lebih lanjut

```java
        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
```

configurasi di atas membutuhkan class JwtConverter yang mengimplementasikan *Converter<Jwt,? extends abraction>*
class ini digunakan agar seolah olah USER dari jwt di convert menjadi *UsernamePasswordAuthenticationToken*
tetapi dengan password Jwt itu sendiri (*tanpa butuh userdetaiservice*)

```java
@Component
public class JwtToUserConverter implements Converter<Jwt, UsernamePasswordAuthenticationToken> {

    @Override
    public UsernamePasswordAuthenticationToken convert(Jwt arg0) {
        UserE user = new UserE();
        user.setId(arg0.getSubject());
        return new UsernamePasswordAuthenticationToken(user, arg0, Collections.emptyList());
    }

}
```

jadi untuk credential nya cuma di butuhkan user dengan id dari jwt kemudian dengan credential apapun (unique Recommended) dan untuk role / grantedAuthority didapat dari token jwt (belum diimplement)

## reference

Dan Vega [https://youtu.be/KYNR5js2cXE](https://youtu.be/KYNR5js2cXE)

coding with max [https://youtu.be/FoyAvzU5fO0](https://youtu.be/FoyAvzU5fO0)
