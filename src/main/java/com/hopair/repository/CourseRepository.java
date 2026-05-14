package com.hopair.repository;

import com.hopair.entity.Course;
import com.hopair.enums.TargetRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByBranchIdAndTargetRole(Long branchId, TargetRole targetRole);
    List<Course> findByCreatedById(Long educatorId);
    List<Course> findByBranchAgencyId(Long agencyId);
}
