package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.AuthenticationUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtTokenService {
    private static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5 * 60 * 60;
    MacAlgorithm alg = Jwts.SIG.HS512; //or HS384 or HS256
    SecretKey key = alg.key().build();

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(key)
                .build().parseSignedClaims(token).getPayload();
    }

    public String generateToken(AuthenticationUser userDetails) {
        return buildToken(userDetails);
    }

    public String buildToken(AuthenticationUser userDetails) {
        return Jwts.builder().subject(userDetails.getUsername())
                .claim("roles", userDetails.getAuthorities())
                .claim("email", userDetails.getEmail())
                .claim("name", userDetails.getName())
                .claim("phone", userDetails.getPhone())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .issuer("https://www.baidu.com")
                .signWith(key)
                .compact();
    }

    public Optional<String> extractUsernameFromToken(String token) {
        if (isTokenExpired(token)) {
            return null;
        }
        return Optional.of(getClaims(token, Claims::getSubject));
    }

    public <T> T getClaims(String token, Function<Claims, T> resolver) {
        return resolver.apply(Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload());
    }


    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


}
