
package com.example.auth.controller;

import com.example.auth.dto.ClaimDto;
import com.example.auth.entity.Claim;
import com.example.auth.service.ClaimService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // Create claim
    @PostMapping
    public ResponseEntity<Claim> createClaim(@RequestBody ClaimDto dto, Authentication auth) {
        Claim claim = claimService.createClaim(
                auth.getName(), dto.getTitle(), dto.getDescription(), dto.getAmount()
        );
        return ResponseEntity.ok(claim);
    }

    // Get all claims
    @GetMapping
    public ResponseEntity<List<Claim>> getAllClaims(Authentication auth) {
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));

        List<Claim> claims = claimService.getAllClaims(auth.getName(), isAdmin);
        return ResponseEntity.ok(claims);
    }

    // Get one claim
    @GetMapping("/{id}")
    public ResponseEntity<Claim> getClaim(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.getClaim(id));
    }

    // Edit claim
    @PutMapping("/{id}")
    public ResponseEntity<Claim> editClaim(@PathVariable Long id,
                                           @RequestBody ClaimDto dto,
                                           Authentication auth) {
        Claim updated = claimService.editClaim(
                auth.getName(), id, dto.getTitle(), dto.getDescription(), dto.getAmount()
        );
        return ResponseEntity.ok(updated);
    }

    // Approve claim
    @PutMapping("/{id}/approve")
    public ResponseEntity<Claim> approveClaim(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.approveOrReject(id, true));
    }

    // Reject claim
    @PutMapping("/{id}/reject")
    public ResponseEntity<Claim> rejectClaim(@PathVariable Long id) {
        return ResponseEntity.ok(claimService.approveOrReject(id, false));
    }
}
