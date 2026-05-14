package com.hopair.controller;

import com.hopair.dto.AgencyDTO;
import com.hopair.dto.CreateAgencyRequest;
import com.hopair.service.AgencyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies")
@RequiredArgsConstructor
public class AgencyController {

    private final AgencyService agencyService;

    @GetMapping
    public ResponseEntity<List<AgencyDTO>> list() {
        return ResponseEntity.ok(agencyService.listAll());
    }

    @PostMapping
    public ResponseEntity<AgencyDTO> create(@Valid @RequestBody CreateAgencyRequest req) {
        return ResponseEntity.ok(agencyService.create(req));
    }
}
