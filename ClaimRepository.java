package com.example.auth.repository;

import com.example.auth.entity.Claim;
import com.example.auth.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
    List<Claim> findByEmployee(AppUser employee);
}

