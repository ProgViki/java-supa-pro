package com.example.auth.service;

import com.example.auth.entity.*;
import com.example.auth.repository.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClaimService {
    private final ClaimRepository claimRepo;
    private final UserRepository userRepo;

    public ClaimService(ClaimRepository claimRepo, UserRepository userRepo) {
        this.claimRepo = claimRepo;
        this.userRepo = userRepo;
    }

    // Create a new claim
    public Claim createClaim(String username, String title, String description, double amount) {
        AppUser user = userRepo.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        Claim claim = Claim.builder()
                .title(title)
                .description(description)
                .amount(amount)
                .employee(user)
                .status(ClaimStatus.PENDING)
                .build();

        return claimRepo.save(claim);
    }

    // Get all claims (admin) or personal (employee)
    public List<Claim> getAllClaims(String username, boolean isAdmin) {
        if (isAdmin) {
            return claimRepo.findAll();
        } else {
            AppUser user = userRepo.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("User not found"));
            return claimRepo.findByEmployee(user);
        }
    }

    // Get one claim
    public Claim getClaim(Long id) {
        return claimRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Claim not found"));
    }

    // Edit a claim (only if pending)
    public Claim editClaim(String username, Long claimId, String title, String description, double amount) {
        Claim claim = getClaim(claimId);

        if (!claim.getEmployee().getUsername().equals(username))
            throw new RuntimeException("Unauthorized");

        if (claim.getStatus() != ClaimStatus.PENDING)
            throw new RuntimeException("Cannot edit approved/rejected claim");

        claim.setTitle(title);
        claim.setDescription(description);
        claim.setAmount(amount);
        claim.setUpdatedAt(LocalDateTime.now());
        return claimRepo.save(claim);
    }

    // Approve or reject (admin only)
    @PreAuthorize("hasRole('ADMIN')")
    public Claim approveOrReject(Long claimId, boolean approve) {
        Claim claim = getClaim(claimId);
        claim.setStatus(approve ? ClaimStatus.APPROVED : ClaimStatus.REJECTED);
        claim.setUpdatedAt(LocalDateTime.now());
        return claimRepo.save(claim);
    }
}

