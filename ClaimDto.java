
package com.example.auth.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClaimDto {
    private String title;
    private String description;
    private double amount;
}
