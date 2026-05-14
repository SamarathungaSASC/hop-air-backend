package com.hopair.controller;

import com.hopair.dto.CreateLessonRequest;
import com.hopair.dto.LessonDTO;
import com.hopair.service.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses/{courseId}/lessons")
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<List<LessonDTO>> list(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.listByCourse(courseId));
    }

    @PostMapping
    public ResponseEntity<LessonDTO> create(
            @PathVariable Long courseId,
            @Valid @RequestBody CreateLessonRequest req) {
        return ResponseEntity.ok(lessonService.create(courseId, req));
    }
}
