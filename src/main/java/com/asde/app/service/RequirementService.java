package com.asde.app.service;

import com.asde.app.domain.Requirement;
import com.asde.app.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class RequirementService implements IRequirementService{
    /* ~    PROPERTIES
    --------------------------------------------------- */
    private RequirementRepository requirementRepo;


    /* ~    METHODS
    --------------------------------------------------- */
    @Autowired
    public RequirementService(RequirementRepository requirementRepo) {
        this.requirementRepo = requirementRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Requirement> getAllRequirements() {
        return requirementRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Requirement findRequirementById(Integer idRequirement) {
        return requirementRepo.findById(idRequirement).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveRequirement(Requirement requirement) {
        requirementRepo.save(requirement);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteRequirementById(Integer idRequirement) {
        requirementRepo.deleteById(idRequirement);
    }

} // end service implementation
