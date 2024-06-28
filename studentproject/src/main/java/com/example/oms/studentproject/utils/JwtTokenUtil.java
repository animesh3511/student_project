package com.example.oms.studentproject.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtTokenUtil {

//    @Value("${jwt.secret}")
//    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    private static final String SECRET_KEY ="DemosecretKeyfgfgdfhdhzdfzsfgfgbsfbxbxbxbdhfdghdghdfhfhfdhfdh";


    public String generateToken(Long studentId)
    {
        Date now = new Date();

        Date expiryDate = new Date(now.getTime()+expiration);


        return Jwts.builder()
                   .setSubject(String.valueOf(studentId))
                   .setIssuedAt(now)
                   .setExpiration(expiryDate)
                //.signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();


    }

    public boolean validate(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token);
            // Log success or any relevant information
            System.out.println("Token validation successful for subject: " + claimsJws.getBody().getSubject());
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            // Log the error for debugging
            System.err.println("Token validation failed: " + e.getMessage());
            return false;
        }
    }


//    public String getUsername(String token) {
//        Claims claims = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).getBody();
//        return claims.getSubject();
//    }

    public Long getStudentIdFromToken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }


//    private SecretKey getSecretKey() {
//        return Keys.hmacShaKeyFor(secret.getBytes());
//    }


}
