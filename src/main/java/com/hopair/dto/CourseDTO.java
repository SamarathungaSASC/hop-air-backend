package com.hopair.dto;

import com.hopair.entity.Course;
import com.hopair.enums.TargetRole;
import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private TargetRole targetRole;
    private Long branchId;
    private String branchName;
    private Long createdById;
    private String createdByName;

    public static CourseDTO from(Course c) {
        CourseDTO dto = new CourseDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setDescription(c.getDescription());
        dto.setTargetRole(c.getTargetRole());
        if (c.getBranch() != null) {
            dto.setBranchId(c.getBranch().getId());
            dto.setBranchName(c.getBranch().getName());
        }
        if (c.getCreatedBy() != null) {
            dto.setCreatedById(c.getCreatedBy().getId());
            dto.setCreatedByName(c.getCreatedBy().getFullName());
        }
        return dto;
    }
}
