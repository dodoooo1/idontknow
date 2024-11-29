package com.idontknow.business.infra.configs.security.auth.providers;

import com.idontknow.business.application.dto.CustomUserDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.MacAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

/**
 * JwtTokenProvider class for generating and validating JSON Web Tokens (JWT) for authentication.
 */
@Component
public class JwtTokenProvider {
    @Value("${jwt.access.token.validity.seconds}")
    private long accessTokenValiditySeconds;

    @Value("${jwt.issuer.url}")
    private String issuerUrl;
    MacAlgorithm alg = Jwts.SIG.HS512; //or HS384 or HS256
    SecretKey key = alg.key().build();

    /**
     * Generates a JSON Web Token (JWT) for the given user details.
     *
     * @param userDetails The user details for which the JWT is generated.
     * @return The generated JWT.
     */
    public String generateToken(CustomUserDetails userDetails) {
        return buildToken(userDetails);
    }

    /**
     * Builds a JWT with the given user details.
     *
     * @param userDetails The user details to be included in the JWT.
     * @return The generated JWT.
     */
    private String buildToken(CustomUserDetails userDetails) {
        return Jwts.builder().subject(userDetails.getUsername())
                .claim("id", userDetails.getId())
                .claim("email", userDetails.getEmail())
                .claim("name", userDetails.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + accessTokenValiditySeconds * 1000))
                .issuer(issuerUrl)
                .signWith(key)
                .compact();
    }

    /**
     * Extracts the username from the given JWT token.
     *
     * @param token The JWT token from which the username is extracted.
     * @return The extracted username.
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * Extracts the expiration date from the given JWT token.
     *
     * @param token The JWT token from which the expiration date is extracted.
     * @return The extracted expiration date.
     */
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    /**
     * Extracts a specific claim from the given JWT token using the provided claim resolver function.
     *
     * @param token          The JWT token from which the claim is extracted.
     * @param claimsResolver The function that resolves the desired claim from the Claims object.
     * @return The extracted claim value.
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extracts all claims from the given JWT token.
     *
     * @param token The JWT token from which all claims are extracted.
     * @return The extracted Claims object.
     */
    public Claims extractAllClaims(String token) {
        try {
            return Jwts.parser()
                    .verifyWith(key)
                    .build().parseSignedClaims(token).getPayload();
        } catch (JwtException | IllegalArgumentException e) {
            if (e instanceof MalformedJwtException) {
                throw new RuntimeException("Invalid JWT token", e);
            }
            if (e instanceof ExpiredJwtException) {
                throw new RuntimeException("JWT has expired", e);
            }
            if (e instanceof UnsupportedJwtException) {
                throw new RuntimeException("Unsupported JWT token", e);
            }
            throw new RuntimeException("JWT claims string is empty", e);
        }
    }

    /**
     * Checks if the given JWT token has expired.
     *
     * @param token The JWT token to be checked.
     * @return True if the token has expired, false otherwise.
     */
    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }
}
