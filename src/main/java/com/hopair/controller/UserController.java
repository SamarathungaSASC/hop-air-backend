package com.hopair.controller;

import com.hopair.dto.CreateUserRequest;
import com.hopair.dto.UserDTO;
import com.hopair.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies/{agencyId}/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> list(@PathVariable Long agencyId) {
        return ResponseEntity.ok(userService.listByAgency(agencyId));
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(
            @PathVariable Long agencyId,
            @Valid @RequestBody CreateUserRequest req) {
        return ResponseEntity.ok(userService.addToAgency(agencyId, req));
    }
}
