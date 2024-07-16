package com.beesidk.projet.service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthenticationService {

    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    public AuthenticationService(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    public ResponseEntity<Map<String, String>> authenticate(String grantType, String refreshToken, String username, String password, boolean withRefrechToken) {

        String subject = null;
        String scope = null;
        Instant now = Instant.now();

        if (grantType.equals("password")) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(auth -> auth.getAuthority())
                    .collect(Collectors.joining(" "));
        } else if (grantType.equals("refreshToken")) {
            if (refreshToken == null) {
                return new ResponseEntity<>(Map.of("error message", "refreshToken is required"), HttpStatus.UNAUTHORIZED);
            }
            Jwt decodeJwt = null;
            try {
                decodeJwt = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("error message", e.getMessage()), HttpStatus.UNAUTHORIZED);
            }

            subject = decodeJwt.getSubject();
            UserDetails user = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            scope = authorities.stream().map(authority -> authority.getAuthority()).collect(Collectors.joining(" "));
        }

        JwtClaimsSet jwaJwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(withRefrechToken ? 1 : 5, ChronoUnit.MINUTES))
                .issuer("security-serice")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwaJwtClaimsSet)).getTokenValue();

        Map<String, String> jwt = new HashMap<>();
        jwt.put("access_token", jwtAccessToken);
        if (withRefrechToken) {
            JwtClaimsSet jwaJwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(now)
                    .expiresAt(now.plus(30, ChronoUnit.MINUTES))
                    .issuer("security-serice")
                    .build();

            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwaJwtClaimsSetRefresh)).getTokenValue();
            jwt.put("refresh_token", jwtRefreshToken);
        }
        return new ResponseEntity<>(jwt, HttpStatus.OK);

    }
}
