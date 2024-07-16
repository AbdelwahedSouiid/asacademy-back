package com.beesidk.projet.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {


    @GetMapping("/testData")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public String getChambres() {
        String x = "Hello World";
        return x;
    }

    @GetMapping("/saveData")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Map<String, String> save() {

        return Map.of("x", "Hello World");
    }

}
