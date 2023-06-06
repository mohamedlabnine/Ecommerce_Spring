package com.example.demo.security;


import com.example.demo.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@Component
public class JwtSecurityFilter extends OncePerRequestFilter {

    private JwtTokenUtil jwtTokenUtil;
    private CustomerService customerService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Allow-Credentials", String.valueOf(true));
        response.setHeader("Access-Control-Max-Age", String.valueOf(180));

        if(request.getRequestURL().toString().contains("http://localhost:8080/api/auth")){
            chain.doFilter(request,response);
            return;
        }

        // Get authorization header and validate
        final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header == null || !header.startsWith("Bearer ") || header.length() <= 7 ) {
            try {
                throw new Exception("your request must contain a token") ;
            } catch (Exception e) {
                response.setHeader("error", "required token");
                response.setContentType(APPLICATION_JSON_VALUE);
                Map<String, String> error = new HashMap<>();
                error.put("error", "required token : " + e.getMessage());
                new ObjectMapper().writeValue(response.getOutputStream(), error);
                return;

            }

        }

        try {
            // Get jwt token and validate
            final String token = header.substring(7);
            final String userEmail = jwtTokenUtil.extractUsername(token);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customerService.loadUserByUsername(userEmail);

                if (jwtTokenUtil.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );
                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }

            }
            chain.doFilter(request, response);
        } catch (Exception e) {
            response.setHeader("error", "invalid token");
            response.setContentType(APPLICATION_JSON_VALUE);
            Map<String, String> error = new HashMap<>();
            error.put("error", "invalid token : " + e.getMessage());
            new ObjectMapper().writeValue(response.getOutputStream(), error);

        }

    }
}