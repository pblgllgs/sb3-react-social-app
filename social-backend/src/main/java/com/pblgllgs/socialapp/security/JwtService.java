package com.pblgllgs.socialapp.security;
/*
 *
 * @author pblgl
 * Created on 01-03-2024
 *
 */

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration-milliseconds}")
    private long expirationTime;

    public String generateToken(Authentication authentication) {

        return Jwts.builder()
                .signWith(key())
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(new Date().getTime() + expirationTime))
                .claim("email", authentication.getName())
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(secret)
        );
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return true;
    }
}
