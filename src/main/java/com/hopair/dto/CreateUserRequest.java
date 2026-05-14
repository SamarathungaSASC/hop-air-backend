package com.hopair.dto;

import com.hopair.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank @Email
    private String email;
    private String fullName;
    @NotNull
    private Role role;
    private Long branchId; // mandatory for CLINICIAN/TRAINEE
}
