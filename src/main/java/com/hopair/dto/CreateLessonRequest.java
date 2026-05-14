package com.hopair.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateLessonRequest {
    @NotBlank
    private String title;
    private String videoUrl;
    private Integer position;
}
