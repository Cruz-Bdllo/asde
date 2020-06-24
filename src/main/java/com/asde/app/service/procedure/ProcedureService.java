package com.asde.app.service.procedure;

import com.asde.app.domain.Procedure;
import com.asde.app.repository.ProcedureRepository;
import com.asde.app.service.procedure.IProcedureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IProcedureService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones básicas CRUD a la tabla <b>tramites</b>.
 */
@Service
public class ProcedureService implements IProcedureService {

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private ProcedureRepository procedureRepo;

    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public ProcedureService (ProcedureRepository procedureRepo) {
        this.procedureRepo = procedureRepo;
    }


    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>tramites</b>.
     * @return Lista de objetos {@link Procedure}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Procedure> getAllProcedures() {
        return procedureRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única generada por
     * la tabla <b>tramites</b>.
     * @param idProcedure Clave única de identificación.
     * @return Objeto {@link Procedure} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Procedure findProcedureById(Integer idProcedure) {
        return procedureRepo.findById(idProcedure).orElse(null);
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su nombre de trámite único
     * de la tabla <b>tramites</b>.
     * @param name String nombre único del trámite.
     * @return Objeto {@link Procedure} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Procedure findProcedureByName(String name) {
        return procedureRepo.findProcedureByName(name).orElse(null);
    }


    /**
     * Método que guarda la entidad {@link Procedure} en la BD en la tabla <b>tramites</b>.
     * @param procedure Objeto {@link Procedure} a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveProcedure(Procedure procedure) {
        procedureRepo.save(procedure);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>tramites</b> dada una clave única
     * de identificación.
     * @param idProcedure Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteProcedureById(Integer idProcedure) {
        procedureRepo.deleteById(idProcedure);
    }
} // end of service procedure
