package com.ecom.sessions.jwts;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private final JwtConfig jwtConfig;

    public String generateRefreshToken(String subject, Object claims) {
        return jwtConfig.getRefreshTokenPrefix() + Jwts.builder()
                .setSubject(subject)
                .claim("user", claims)
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
                .signWith(jwtConfig.refreshSecretKey())
                .compact();
    }

    public String generateAccessToken(String subject, String claims) {
        try {
            return jwtConfig.getAccessTokenPrefix() + Jwts.builder()
                    .setSubject(subject)
                    .claim("authority", claims)
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(1)))
                    .signWith(jwtConfig.accessSecretKey())
                    .compact();
        } catch (ExpiredJwtException ee) {
            throw new ExpiredJwtException(ee.getHeader(), ee.getClaims(), ee.getMessage());
        } catch (SignatureException se) {
            throw new SignatureException(se.getMessage());
        } catch (MissingClaimException mce) {
            throw new MissingClaimException(mce.getHeader(), mce.getClaims(), mce.getMessage());
        } catch (MalformedJwtException malformedJwtException) {
            throw new MalformedJwtException(malformedJwtException.getMessage());
        } catch (UnsupportedJwtException uje) {
            throw new UnsupportedJwtException(uje.getMessage());
        } catch (JwtException je) {
            throw new JwtException(je.getMessage());
        }
    }

}