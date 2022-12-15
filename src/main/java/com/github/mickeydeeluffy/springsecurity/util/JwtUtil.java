package com.github.mickeydeeluffy.springsecurity.util;

import com.github.mickeydeeluffy.springsecurity.entity.UserDetail;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
//    @Value("{constants.jwt.key}")
    private final String SECRET_KEY = "thiskeyissuperbthiskeyissuperbthiskeyissuperbthiskeyissuperb";
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    @Value("{variables.jwt.token_expiry_date}")
    private String TOKEN_EXPIRY_DATE;

//    private final SecretKey key =  Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String generateToken(UserDetails userDetail) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetail.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(LocalDateTime.now().plusHours(10L).toInstant(ZoneOffset.UTC)))
                .signWith( SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();

    }


    public Boolean validateToken(String token, UserDetails userDetail) {
        final String username = extractUsername(token);
        return (username.equals(userDetail.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token)
                .before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public String extractUsername(String token) {
       return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
