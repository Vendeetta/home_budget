package ru.gerasimov.home_budget.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.gerasimov.home_budget.service.JwtService;

import java.io.IOException;

/**
 * Конфигурационный класс Spring Security.
 *
 * @author Evgeniy Gerasimov.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /**
     * Сервис для работы с Jwt.
     */
    private final JwtService service;

    /**
     * Элемент цепочки для фильтрации запроса.
     * @param request запрос.
     * @param response ответ.
     * @param filterChain элемент цепочки.
     * @throws ServletException выбрасываемое исключение.
     * @throws IOException выбрасываемое исключение.
     */
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        final String username;

        if (header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }
        final String jwt = header.substring(7);
        username = service.extractUsername(jwt);
    }
}
