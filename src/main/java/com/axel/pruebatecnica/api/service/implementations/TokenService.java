package com.axel.pruebatecnica.api.service.implementations;

import com.axel.pruebatecnica.api.service.interfaces.ITokenService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;

import static com.axel.pruebatecnica.api.configuration.filter.TokenJwtConfig.SECRET_KEY;
//security
@Service
public class TokenService implements ITokenService {

    @Override
    public String generateToken(Authentication authentication) {

        User user = (org.springframework.security.core.userdetails.User) authentication.getPrincipal();
        String username = user.getUsername();
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        Claims claims = null;

        try {
            claims = Jwts.claims()
                    .add("authorities", new ObjectMapper().writeValueAsString(roles))
                    .add("username", username)
                    .build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

        return Jwts.builder()
                .subject(username)
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 3600000))
                .issuedAt(new Date())
                .signWith(SECRET_KEY)
                .compact();
    }
}
