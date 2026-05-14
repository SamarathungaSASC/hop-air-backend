package com.hopair.service;

import com.hopair.dto.CourseDTO;
import com.hopair.dto.CreateCourseRequest;
import com.hopair.entity.Branch;
import com.hopair.entity.Course;
import com.hopair.entity.User;
import com.hopair.enums.TargetRole;
import com.hopair.repository.BranchRepository;
import com.hopair.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final BranchRepository branchRepository;

    /** Returns courses for the educator's agency */
    public List<CourseDTO> listForEducator(User educator) {
        Long agencyId = educator.getAgency().getId();
        return courseRepository.findByBranchAgencyId(agencyId).stream().map(CourseDTO::from).toList();
    }

    /** Returns courses filtered by branch + role for clinician/trainee */
    public List<CourseDTO> listForStaff(User user) {
        TargetRole targetRole = switch (user.getRole()) {
            case CLINICIAN -> TargetRole.CLINICIAN;
            case TRAINEE   -> TargetRole.TRAINEE;
            default -> throw new IllegalStateException("Not a staff role");
        };
        Long branchId = user.getBranch().getId();
        return courseRepository.findByBranchIdAndTargetRole(branchId, targetRole)
            .stream().map(CourseDTO::from).toList();
    }

    @Transactional
    public CourseDTO create(User educator, CreateCourseRequest req) {
        Branch branch = branchRepository.findById(req.getBranchId())
            .orElseThrow(() -> new RuntimeException("Branch not found"));

        Course course = new Course();
        course.setName(req.getName());
        course.setDescription(req.getDescription());
        course.setTargetRole(req.getTargetRole());
        course.setBranch(branch);
        course.setCreatedBy(educator);
        return CourseDTO.from(courseRepository.save(course));
    }

    public CourseDTO getById(Long id) {
        return courseRepository.findById(id).map(CourseDTO::from)
            .orElseThrow(() -> new RuntimeException("Course not found"));
    }
}
