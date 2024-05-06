package de.ait.cohort34.accounting.controller;

import ait.cohort34.accounting.dto.auth.AuthRequest;
import ait.cohort34.accounting.dto.auth.AuthResponse;
import ait.cohort34.security.auth.JwtTokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider tokenProvider;

    @PostMapping
    public ResponseEntity<AuthResponse> authenticateManager(@Valid @RequestBody AuthRequest authRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authRequest.getManagerName(),
                        authRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String managerNameFromRequest = authRequest.getManagerName();

        log.info("\n manager name from request : " + managerNameFromRequest + "\n");

        String jwt = tokenProvider.createToken(managerNameFromRequest);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }


    @GetMapping
    public ResponseEntity<?> authenticateAndGetToken() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // Проверяем, что текущий пользователь не анонимен
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: No token generated for anonymous user");
        } else {
            String username = authentication.getName(); // Получаем имя пользователя из аутентификации
            log.info("\n manager name from request : " + username + "\n");
            String token = tokenProvider.createToken(username); // Генерация JWT
            return ResponseEntity.ok(new AuthResponse(token)); // Отправляем токен клиенту
        }
    }

}
