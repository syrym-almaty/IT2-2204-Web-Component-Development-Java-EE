package com.example.demo.config;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.demo.security.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtRequestFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private static final String[] SWAGGER_PATHS = {
            "/swagger-ui.html",
            "/v3/api-docs.*",
            "/swagger-ui.*",
            "/webjars/swagger-ui.*",
            "/api-docs.*",
            "/auth.*"
    };

    private boolean isSwaggerPath(String path) {
        for (String swaggerPath : SWAGGER_PATHS) {
            if (path.matches(swaggerPath)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String requestPath = request.getRequestURI(); // Получаем URI запроса
        // Проверяем, если путь запроса относится к Swagger или другим исключенным путям
        if (isSwaggerPath(requestPath)) {
            System.out.println(requestPath + "did it");
            // Пропускаем запрос без проверки JWT
            filterChain.doFilter(request, response);
            return;
        }
        // Rest of your JWT processing logic
        String authHeader = request.getHeader("Authorization");

        // JWT отсутствует или неверный формат
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            sendCustomError(response, "Unauthorized", HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }



        String token = authHeader.substring(7);
        try {
            String username = jwtUtil.validateTokenAndRetrieveClaim(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            if (SecurityContextHolder.getContext().getAuthentication() == null) {
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (JWTVerificationException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid JWT Token");
            return;
        }

        filterChain.doFilter(request, response);
    }
    private void sendCustomError(HttpServletResponse response, String message, int statusCode) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String jsonResponse = String.format("{\"message\": \"%s\", \"statusCode\": %d}", message, statusCode);
        response.getWriter().write(jsonResponse);
    }
}
