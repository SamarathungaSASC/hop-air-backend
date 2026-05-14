package com.hopair.dto;

import com.hopair.entity.Branch;
import lombok.Data;

@Data
public class BranchDTO {
    private Long id;
    private String name;
    private String ccn;
    private Long agencyId;
    private String agencyName;

    public static BranchDTO from(Branch b) {
        BranchDTO dto = new BranchDTO();
        dto.setId(b.getId());
        dto.setName(b.getName());
        dto.setCcn(b.getCcn());
        if (b.getAgency() != null) {
            dto.setAgencyId(b.getAgency().getId());
            dto.setAgencyName(b.getAgency().getName());
        }
        return dto;
    }
}
