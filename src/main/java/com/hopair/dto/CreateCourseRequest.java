package com.hopair.dto;

import com.hopair.enums.TargetRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCourseRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull
    private TargetRole targetRole;
    @NotNull
    private Long branchId;
}
