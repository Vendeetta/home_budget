package ru.gerasimov.home_budget.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.function.Function;

/**
 * Сервис для работы с JWT токеном.
 */
@Service
public class JwtService {

    /**
     * Секретный ключ для генерации и чтения JWT токена.
     */
    private final static String KEY = "dfsu428762i3hesa9d8u1lskaduy01hda0`ph`!lhe2q3iq6r4oL&U^%(kd";

    /**
     * Извлечение username из JWT токена.
     *
     * @param token JWT токен.
     * @return username.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Извлечение одного значения из JWT токена.
     *
     * @param token         JWT токен.
     * @param claimResolver функция извлечения нужного параметра.
     * @return извлеченный параметр.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        var allClaims = extractAllClaims(token);
        return claimResolver.apply(allClaims);
    }

    /**
     * Извлечение всех параметров из JWT токена.
     *
     * @param token JWT токен.
     * @return параметры из body JWT токена.
     */
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Генерация секретного ключа.
     *
     * @return секретный ключ.
     */
    private Key getSecretKey() {
        byte[] key = Decoders.BASE64.decode(KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
