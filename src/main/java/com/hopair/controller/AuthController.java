package com.hopair.controller;

import com.hopair.dto.UserDTO;
import com.hopair.entity.User;
import com.hopair.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Called by frontend on every login.
     * Resolves the logged-in user from the JWT and returns their profile + role.
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> me(@AuthenticationPrincipal Jwt jwt) {
        User user = authService.resolveUser(jwt);
        return ResponseEntity.ok(UserDTO.from(user));
    }
}
