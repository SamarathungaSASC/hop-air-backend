package com.hopair.controller;

import com.hopair.dto.BranchDTO;
import com.hopair.dto.CreateBranchRequest;
import com.hopair.service.BranchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agencies/{agencyId}/branches")
@RequiredArgsConstructor
public class BranchController {

    private final BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchDTO>> list(@PathVariable Long agencyId) {
        return ResponseEntity.ok(branchService.listByAgency(agencyId));
    }

    @PostMapping
    public ResponseEntity<BranchDTO> create(
            @PathVariable Long agencyId,
            @Valid @RequestBody CreateBranchRequest req) {
        return ResponseEntity.ok(branchService.create(agencyId, req));
    }
}
