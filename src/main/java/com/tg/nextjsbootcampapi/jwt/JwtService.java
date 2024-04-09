package com.tg.nextjsbootcampapi.jwt;


import com.tg.nextjsbootcampapi.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

  @Value("${jwt.secret.key}")
  private String SECRET_KEY;

  public boolean isValid(String token, UserDetails userDetails) {

    String username = extractUsername(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

  }

  private boolean isTokenExpired(String token) {

    return extractExpiration(token).before(new Date());
  }

  private Date extractExpiration(String token) {
    return extractClaims(token, Claims::getExpiration);
  }

  private <T> T extractClaims(String token, Function<Claims, T> resolver) {
    Claims claims = extractAllClaims(token);
    return resolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts
          .parser()
          .verifyWith(getSigningKey())
          .build()
          .parseSignedClaims(token)
          .getPayload();
  }

  private SecretKey getSigningKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }

  public String extractUsername(String token) {
    return extractClaims(token, Claims::getSubject);
  }


  public String generateToken(User user) {
    return Jwts
        .builder()
        .subject(user.getEmail())
        .issuedAt( new Date(System.currentTimeMillis()))
        .expiration( new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
        .signWith(getSigningKey())
        .compact();
  }

}
