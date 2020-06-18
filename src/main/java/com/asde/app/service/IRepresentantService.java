package com.asde.app.service;


import com.asde.app.domain.Representant;

import java.util.List;

public interface IRepresentantService {

    List<Representant> getAllRepresentants();

    void saveRepresentant(Representant representant);

}
