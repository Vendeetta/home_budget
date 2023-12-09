package ru.gerasimov.home_budget.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Сервис для работы с JWT токеном.
 */
@Service
public class JwtService {

    /**
     * Секретный ключ для генерации и чтения JWT токена.
     */
    private final static String KEY = "dfsu428762i3hesa9d8u1lskaduy01hda0phlhe2q3iq6r4oLUsdfsdf8743jfdkkd";

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

    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> claims,
            UserDetails userDetails
    ) {
        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = userDetails.getUsername();
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
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
