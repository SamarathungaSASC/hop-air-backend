package com.hopair.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateBranchRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String ccn;
}
