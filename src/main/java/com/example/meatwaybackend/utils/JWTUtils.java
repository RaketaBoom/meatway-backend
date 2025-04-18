package com.example.meatwaybackend.utils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.stereotype.Component;

/**
 * Утилитный класс для генерации JSON Web Token (JWT).
 * <p>
 * Токен содержит информацию о email пользователя и действителен в течение 2 недель.
 * </p>
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JWTUtils {

    private final JwtEncoder encoder;
    private final JwtDecoder decoder;

    /**
     * Генерирует новый access JWT для указанного пользователя.
     *
     * @param username email пользователя, для которого создается токен
     * @return строковое представление сгенерированного JWT
     * @throws IllegalArgumentException если {@code username} пустой или null
     */
    public String generateAccessToken(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("meatway.ru")
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.HOURS))
                .subject(username)
                .claim("token_type", "access")
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.debug("Token created for user: {}", username);

        return token;
    }

    /**
     * Генерирует новый JWT для указанного пользователя.
     *
     * @param username email пользователя, для которого создается токен
     * @return строковое представление сгенерированного JWT
     * @throws IllegalArgumentException если {@code username} пустой или null
     */
    public String generateRefreshToken(String username) {

        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username cannot be empty or null.");
        }

        Instant now = Instant.now();
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("meatway.ru")
                .issuedAt(now)
                .expiresAt(now.plus(14, ChronoUnit.DAYS))
                .subject(username)
                .claim("token_type", "refresh")
                .build();

        String token = this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        log.debug("Token created for user: {}", username);

        return token;
    }

    /**
     * Извлекает имя пользователя из заданного JWT (в данном случае email).
     *
     * @param token JWT, из которого необходимо извлечь имя пользователя
     * @return имя пользователя, указанное в токене
     * @throws IllegalArgumentException если токен не является действительным
     */
    public String extractUsername(String token) {
        Jwt jwt = decodeJwt(token);
        validateTokenTimes(jwt);

        String username = jwt.getSubject();

        log.info("Extracted username from token: {}", username);

        return username;
    }

    private Jwt decodeJwt(String token) {
        try {
            return this.decoder.decode(token);

        } catch (JwtException e) {

            log.error("Invalid JWT token: {}", e.getMessage());
            throw new IllegalArgumentException("Invalid JWT token", e);
        }
    }

    private void validateTokenTimes(Jwt jwt) {
        Instant now = Instant.now();

        validateTime(jwt.getExpiresAt(), now, "JWT token has expired", false);
        validateTime(jwt.getNotBefore(), now, "JWT token is not yet valid", true);
    }

    private void validateTime(Instant tokenTime, Instant now, String errorMessage, boolean isNotBefore) {
        if (tokenTime != null) {

            boolean invalid = isNotBefore ? tokenTime.isAfter(now) : tokenTime.isBefore(now);

            if (invalid) {
                log.error(errorMessage);
                throw new IllegalArgumentException(errorMessage);
            }
        }
    }
}
