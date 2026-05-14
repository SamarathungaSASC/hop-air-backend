package com.hopair.service;

import com.hopair.dto.BranchDTO;
import com.hopair.dto.CreateBranchRequest;
import com.hopair.entity.Agency;
import com.hopair.entity.Branch;
import com.hopair.repository.AgencyRepository;
import com.hopair.repository.BranchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final AgencyRepository agencyRepository;

    public List<BranchDTO> listByAgency(Long agencyId) {
        return branchRepository.findByAgencyId(agencyId).stream().map(BranchDTO::from).toList();
    }

    @Transactional
    public BranchDTO create(Long agencyId, CreateBranchRequest req) {
        if (branchRepository.existsByCcn(req.getCcn())) {
            throw new IllegalArgumentException("CCN already exists: " + req.getCcn());
        }
        Agency agency = agencyRepository.findById(agencyId)
            .orElseThrow(() -> new RuntimeException("Agency not found"));
        Branch branch = new Branch();
        branch.setName(req.getName());
        branch.setCcn(req.getCcn());
        branch.setAgency(agency);
        return BranchDTO.from(branchRepository.save(branch));
    }
}
