package com.hopair.dto;

import com.hopair.entity.Lesson;
import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String videoUrl;
    private Long courseId;
    private Integer position;

    public static LessonDTO from(Lesson l) {
        LessonDTO dto = new LessonDTO();
        dto.setId(l.getId());
        dto.setTitle(l.getTitle());
        dto.setVideoUrl(l.getVideoUrl());
        dto.setCourseId(l.getCourse().getId());
        dto.setPosition(l.getPosition());
        return dto;
    }
}
