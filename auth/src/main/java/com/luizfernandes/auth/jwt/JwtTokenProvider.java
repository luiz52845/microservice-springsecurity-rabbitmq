package com.luizfernandes.auth.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Service
public class JwtTokenProvider {


    @Value("${security.jwt.token.secret-key}")
    private String secretkeyString;

    private Key secretKey;

    @Value("${security.jwt.token.expire-length}")
    private Long expire;

    @PostConstruct
    protected void init() {
        //secretkey = Base64.getEncoder().encodeToString(secretkey.getBytes());
        //this.secretkey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        this.secretKey = Keys.hmacShaKeyFor(secretkeyString.getBytes());
    }

    @Qualifier("UserService")
    private UserDetailsService userDetailsService;


    public String createToken(String username, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", roles);

        Date now = new Date();
        Date validate = new Date(now.getTime() + expire);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validate)
                .signWith(secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUserName(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getUserName(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token).getBody().getSubject();
    }

    public String resolveToken(HttpServletRequest req) {
        String bearerToken = req.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

    public boolean validadeToken(String token) {
        try {
            Jwt<Header, Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token);
            if (claims.getBody().getExpiration().before(new Date())) {
                return false;
            }
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }

    }


}

