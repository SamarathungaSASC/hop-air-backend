package com.hopair.controller;

import com.hopair.dto.CourseDTO;
import com.hopair.dto.CreateCourseRequest;
import com.hopair.entity.User;
import com.hopair.enums.Role;
import com.hopair.service.AuthService;
import com.hopair.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final AuthService authService;

    @GetMapping
    public ResponseEntity<List<CourseDTO>> list(@AuthenticationPrincipal Jwt jwt) {
        User user = authService.resolveUser(jwt);
        List<CourseDTO> courses = switch (user.getRole()) {
            case SUPERADMIN, EDUCATOR -> courseService.listForEducator(user);
            case CLINICIAN, TRAINEE   -> courseService.listForStaff(user);
        };
        return ResponseEntity.ok(courses);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(
            @AuthenticationPrincipal Jwt jwt,
            @Valid @RequestBody CreateCourseRequest req) {
        User user = authService.resolveUser(jwt);
        if (user.getRole() != Role.EDUCATOR && user.getRole() != Role.SUPERADMIN) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(courseService.create(user, req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTO> get(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getById(id));
    }
}
