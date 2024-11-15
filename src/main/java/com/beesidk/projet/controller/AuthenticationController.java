package com.beesidk.projet.controller;


import com.beesidk.projet.service.securityService.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController

public class AuthenticationController {

    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(String username, String password, Boolean withRefreshToken) {

        return authenticationService.login(username, password, withRefreshToken);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<Map<String, String>> login(String refreshToken) {
        return authenticationService.refreshAccessToken(refreshToken);
    }


}
