package com.asde.app.service;


import com.asde.app.domain.Representant;

import java.util.List;

public interface IRepresentantService {

    List<Representant> getAllRepresentants();

    Representant getRepresentantById(Integer idRepresentant);

    void saveRepresentant(Representant representant);

    void deleteRepresentantById(Integer IdRepresentant);

}
