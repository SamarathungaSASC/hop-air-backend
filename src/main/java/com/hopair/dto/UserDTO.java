package com.hopair.dto;

import com.hopair.entity.User;
import com.hopair.enums.Role;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String fullName;
    private Role role;
    private Long agencyId;
    private String agencyName;
    private Long branchId;
    private String branchName;

    public static UserDTO from(User u) {
        UserDTO dto = new UserDTO();
        dto.setId(u.getId());
        dto.setEmail(u.getEmail());
        dto.setFullName(u.getFullName());
        dto.setRole(u.getRole());
        if (u.getAgency() != null) {
            dto.setAgencyId(u.getAgency().getId());
            dto.setAgencyName(u.getAgency().getName());
        }
        if (u.getBranch() != null) {
            dto.setBranchId(u.getBranch().getId());
            dto.setBranchName(u.getBranch().getName());
        }
        return dto;
    }
}
