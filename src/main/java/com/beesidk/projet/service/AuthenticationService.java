package com.beesidk.projet.service;


import com.beesidk.projet.entity.AppUser;
import com.beesidk.projet.repository.AppUserRepository;
import lombok.AllArgsConstructor;
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
import java.util.HashMap;
import java.util.Map;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final AppUserRepository appUserRepository;

    // Méthode pour la connexion
    public ResponseEntity<Map<String, String>> login(String username, String password, boolean withRefreshToken) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        AppUser appUser = appUserRepository.findByEmail(userDetails.getUsername()).orElseThrow();
        String subject = authentication.getName();
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String image_url = appUser.getPhoto();
        return generateTokens(subject, scope, image_url, withRefreshToken);
    }

    // Méthode pour le rafraîchissement de jeton
    public ResponseEntity<Map<String, String>> refreshAccessToken(String refreshToken) {
        if (refreshToken == null) {
            return new ResponseEntity<>(Map.of("error message", "refreshToken is required"), HttpStatus.UNAUTHORIZED);
        }
        Jwt decodedJwt;
        try {
            decodedJwt = jwtDecoder.decode(refreshToken);
        } catch (JwtException e) {
            return new ResponseEntity<>(Map.of("error message", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }

        String subject = decodedJwt.getSubject();
        UserDetails user = userDetailsService.loadUserByUsername(subject);
        AppUser appUser = appUserRepository.findByEmail(user.getUsername()).orElseThrow();
        String scope = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        String image_url = appUser.getPhoto();
        return generateTokens(subject, scope, image_url, false);
    }

    // Méthode pour générer les jetons d'accès et de rafraîchissement
    private ResponseEntity<Map<String, String>> generateTokens(String subject, String scope, String imageUrl, boolean withRefreshToken) {
        Instant now = Instant.now();

        JwtClaimsSet accessTokenClaims = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(now)
                .expiresAt(now.plus(5, ChronoUnit.DAYS))
                .issuer("security-service")
                .claim("scope", scope)
                .claim("image_url", imageUrl)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(accessTokenClaims)).getTokenValue();

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", jwtAccessToken);

        if (withRefreshToken) {
            JwtClaimsSet refreshTokenClaims = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(now)
                    .expiresAt(now.plus(1, ChronoUnit.DAYS))
                    .issuer("security-service")
                    .build();

            String jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(refreshTokenClaims)).getTokenValue();
            tokens.put("refresh_token", jwtRefreshToken);
        }

        return new ResponseEntity<>(tokens, HttpStatus.OK);
    }
}

