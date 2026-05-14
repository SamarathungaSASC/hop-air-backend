package com.hopair.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateAgencyRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String educatorEmail;
}
