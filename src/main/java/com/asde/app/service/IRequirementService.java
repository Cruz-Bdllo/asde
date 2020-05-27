package com.asde.app.service;

import com.asde.app.domain.Requirement;

import java.util.List;

public interface IRequirementService {

    List<Requirement> getAllRequirements();

    Requirement findRequirementById(Integer idRequirement);

    void saveRequirement(Requirement requirement);

    void deleteRequirementById(Integer idRequirement);

}
