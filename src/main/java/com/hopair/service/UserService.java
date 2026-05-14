package com.hopair.service;

import com.hopair.dto.CreateUserRequest;
import com.hopair.dto.UserDTO;
import com.hopair.entity.Agency;
import com.hopair.entity.Branch;
import com.hopair.entity.User;
import com.hopair.enums.Role;
import com.hopair.repository.AgencyRepository;
import com.hopair.repository.BranchRepository;
import com.hopair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final AgencyRepository agencyRepository;
    private final BranchRepository branchRepository;

    public List<UserDTO> listByAgency(Long agencyId) {
        return userRepository.findByAgencyId(agencyId).stream().map(UserDTO::from).toList();
    }

    @Transactional
    public UserDTO addToAgency(Long agencyId, CreateUserRequest req) {
        Agency agency = agencyRepository.findById(agencyId)
            .orElseThrow(() -> new RuntimeException("Agency not found"));

        if (req.getRole() == Role.CLINICIAN || req.getRole() == Role.TRAINEE) {
            if (req.getBranchId() == null) {
                throw new IllegalArgumentException("Branch is mandatory for CLINICIAN and TRAINEE");
            }
        }

        User user = new User();
        user.setEmail(req.getEmail());
        user.setFullName(req.getFullName());
        user.setRole(req.getRole());
        user.setAgency(agency);

        if (req.getBranchId() != null) {
            Branch branch = branchRepository.findById(req.getBranchId())
                .orElseThrow(() -> new RuntimeException("Branch not found"));
            user.setBranch(branch);
        }

        return UserDTO.from(userRepository.save(user));
    }
}
