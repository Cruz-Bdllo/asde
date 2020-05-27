package com.asde.app.service;

import com.asde.app.domain.Dependence;
import com.asde.app.repository.DependenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class DependenceService implements IDependenceService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private DependenceRepository dependenceRepo;


    /* ~    METHODS
    --------------------------------------------------- */
    @Autowired
    public DependenceService(DependenceRepository dependenceRepo) {
        this.dependenceRepo = dependenceRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Dependence> getAllDependecies() {
        return dependenceRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Dependence findDependenceById(Integer idDependence) {
        return dependenceRepo.findById(idDependence).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveDependence(Dependence dependence) {
        dependenceRepo.save(dependence);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteDependenceById(Integer idDependence) {
        dependenceRepo.deleteById(idDependence);
    }
} // end service implementation
