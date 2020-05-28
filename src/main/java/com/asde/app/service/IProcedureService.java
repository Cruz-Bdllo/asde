package com.asde.app.service;

import com.asde.app.domain.Procedure;

import java.util.List;

public interface IProcedureService {

    List<Procedure> getAllProcedures();

    Procedure findProcedureById(Integer idProcedure);

    Procedure findProcedureByName(String name);

    void saveProcedure(Procedure procedure);

    void deleteProcedureById(Integer idProcedure);

} // end service interface
