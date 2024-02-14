package com.joshuapavan.grandeur.services.impl;

import com.joshuapavan.grandeur.services.JWTService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JWTServiceImpl implements JWTService {
    @Override
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    @Override
    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .signWith(getSigninKey(), SignatureAlgorithm.HS256)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24)))
                .compact();
    }

    @Override
    public String generateRefreshToken(UserDetails userDetails){
        return Jwts.builder()
                .signWith(getSigninKey())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (1000 * 60 * 24) * 7))
                .compact();
    }

    private Key getSigninKey() {
        byte[] bytesKey = Decoders.BASE64.decode("1alhJvqdBcR5OU8tIfkf4EL9CV+wIPvp4XajmS1gSKDtGYCU01eHCct49eUFpzhMtoAh0TWh64vn58OEneZhDglgV7mmbz7bw9S96oS9lfHzJ5+9yeyw1kjQfB+kCKpkcYEs0jAcThNJjtrDGwZUrwjYTn/Dnh8rIWJjRfysha32uLrW/QKsOOKbLmJqoEqc5GCAZ3uISsdhQOdz8EG9+1iHSzJ2MWejt5IMGeHDk66ZS9mh4r4TMxmeiLkirFB23FF6H2jfTVH8Ru9WcKmHfEtaILBK9KT8BWBN4gjrCvoND68/2nWPFiPA8PXURbQKEBhZ8TNApvpqlxYtZu5Hz5nrifkcmJF7Vl5vDlSdEJc=");
        return Keys.hmacShaKeyFor(bytesKey);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolvers){
        Claims claims = extractAllClaims(token);

        return claimResolvers.apply(claims);
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    @Override
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }
}
