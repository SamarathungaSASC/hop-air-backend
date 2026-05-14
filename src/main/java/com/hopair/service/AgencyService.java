package com.hopair.service;

import com.hopair.dto.AgencyDTO;
import com.hopair.dto.CreateAgencyRequest;
import com.hopair.entity.Agency;
import com.hopair.entity.User;
import com.hopair.enums.Role;
import com.hopair.repository.AgencyRepository;
import com.hopair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgencyService {

    private final AgencyRepository agencyRepository;
    private final UserRepository userRepository;

    public List<AgencyDTO> listAll() {
        return agencyRepository.findAll().stream().map(AgencyDTO::from).toList();
    }

    @Transactional
    public AgencyDTO create(CreateAgencyRequest req) {
        if (agencyRepository.existsByName(req.getName())) {
            throw new IllegalArgumentException("Agency name already exists");
        }
        Agency agency = new Agency();
        agency.setName(req.getName());
        agencyRepository.save(agency);

        // Provision educator
        User educator = new User();
        educator.setEmail(req.getEducatorEmail());
        educator.setRole(Role.EDUCATOR);
        educator.setAgency(agency);
        userRepository.save(educator);

        return AgencyDTO.from(agency);
    }
}
