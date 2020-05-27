package com.asde.app.service;

import com.asde.app.domain.Procedure;
import com.asde.app.repository.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class ProcedureService implements IProcedureService {
    @Autowired
    private ProcedureRepository procedureRepo;

//    @Autowired
//    public ProcedureService (ProcedureRepository procedureRepo) {
//        this.procedureRepo = procedureRepo;
//    }

    @Override
    @Transactional(readOnly = true)
    public List<Procedure> getAllProcedures() {
        return procedureRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Procedure findProcedureById(Integer idProcedure) {
        return procedureRepo.findById(idProcedure).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Procedure findProcedureByName(String name) {
        return procedureRepo.findProcedureByName(name).orElse(null);
    }

    @Override
    @Transactional(readOnly = false)
    public void saveProcedure(Procedure procedure) {
        procedureRepo.save(procedure);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProcedureById(Integer idProcedure) {
        procedureRepo.deleteById(idProcedure);
    }

} // end of service procedure
