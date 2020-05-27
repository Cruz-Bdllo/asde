package com.asde.app.service;

import com.asde.app.domain.Dependence;

import java.util.List;

public interface IDependenceService {

    List<Dependence> getAllDependecies();

    Dependence findDependenceById(Integer idDependence);

    void saveDependence(Dependence dependence);

    void deleteDependenceById(Integer idDependence);

}
