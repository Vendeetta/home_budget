package ru.gerasimov.home_budget.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gerasimov.home_budget.dto.AuthenticationRequest;
import ru.gerasimov.home_budget.dto.AuthenticationResponse;
import ru.gerasimov.home_budget.dto.RegisterRequest;
import ru.gerasimov.home_budget.model.Role;
import ru.gerasimov.home_budget.model.User;
import ru.gerasimov.home_budget.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .email(registerRequest.email())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        String token = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @PostMapping("/auth")
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.email(),
                authenticationRequest.password()
        ));
        String email = authenticationRequest.email();
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        String token = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }
}
