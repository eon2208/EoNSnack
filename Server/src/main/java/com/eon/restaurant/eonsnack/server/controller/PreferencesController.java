package com.eon.restaurant.eonsnack.server.controller;

import com.eon.restaurant.eonsnack.server.entity.User;
import com.eon.restaurant.eonsnack.server.payload.request.PreferencesRequest;
import com.eon.restaurant.eonsnack.server.service.PreferencesService;
import com.eon.restaurant.eonsnack.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/preferences", produces = MediaType.APPLICATION_JSON_VALUE)
public class PreferencesController {

    private final PreferencesService preferencesService;

    private final UserService userService;

    public PreferencesController(PreferencesService preferencesService, UserService userService) {
        this.preferencesService = preferencesService;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/preferences")
    public void savePreferences(@RequestBody PreferencesRequest preferencesRequest, Authentication authentication) {

        User user = userService.findByUsername(authentication.getName());

        preferencesService.saveUserPreferences(preferencesRequest, user);
    }

}