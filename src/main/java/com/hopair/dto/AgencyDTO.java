package com.hopair.dto;

import com.hopair.entity.Agency;
import lombok.Data;

@Data
public class AgencyDTO {
    private Long id;
    private String name;

    public static AgencyDTO from(Agency a) {
        AgencyDTO dto = new AgencyDTO();
        dto.setId(a.getId());
        dto.setName(a.getName());
        return dto;
    }
}
