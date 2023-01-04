package com.belajarjwt.controllers;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rootcontroller
 */
@RestController
@RequestMapping("/")
public class Rootcontroller {

    @GetMapping("home")
    public String home() {
        return "ini home";
    }

    @GetMapping("about")
    public String about(Principal principal) {
        return principal.getName();
    }
}