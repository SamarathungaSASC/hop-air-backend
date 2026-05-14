package com.hopair.service;

import com.hopair.dto.CreateLessonRequest;
import com.hopair.dto.LessonDTO;
import com.hopair.entity.Course;
import com.hopair.entity.Lesson;
import com.hopair.repository.CourseRepository;
import com.hopair.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseRepository courseRepository;

    public List<LessonDTO> listByCourse(Long courseId) {
        return lessonRepository.findByCourseIdOrderByPosition(courseId)
            .stream().map(LessonDTO::from).toList();
    }

    @Transactional
    public LessonDTO create(Long courseId, CreateLessonRequest req) {
        Course course = courseRepository.findById(courseId)
            .orElseThrow(() -> new RuntimeException("Course not found"));
        Lesson lesson = new Lesson();
        lesson.setTitle(req.getTitle());
        lesson.setVideoUrl(req.getVideoUrl());
        lesson.setCourse(course);
        lesson.setPosition(req.getPosition() != null ? req.getPosition() : 0);
        return LessonDTO.from(lessonRepository.save(lesson));
    }
}
