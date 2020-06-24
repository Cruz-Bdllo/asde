package com.asde.app.service.requirement;

import com.asde.app.domain.Requirement;
import com.asde.app.repository.RequirementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IRequirementService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones básicas CRUD a la tabla <b>requisitos</b>.
 */
@Service
public class RequirementService implements IRequirementService{
    /* ~    PROPERTIES
    --------------------------------------------------- */
    private RequirementRepository requirementRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public RequirementService(RequirementRepository requirementRepo) {
        this.requirementRepo = requirementRepo;
    }


    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>requisitos</b>.
     * @return Lista de objetos {@link Requirement}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Requirement> getAllRequirements() {
        return (List<Requirement>) requirementRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única generada por
     * la tabla <b>requisitos</b>.
     * @param idRequirement Clave única de identificación.
     * @return Objeto {@link Requirement} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Requirement findRequirementById(Integer idRequirement) {
        return requirementRepo.findById(idRequirement).orElse(null);
    }


    /**
     * Método que guarda la entidad {@link Requirement} en la BD en la tabla <b>requisitos</b>.
     * @param requirement Objeto {@link Requirement} a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveRequirement(Requirement requirement) {
        requirementRepo.save(requirement);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>requisitos</b> dada una clave única
     * de identificación.
     * @param idRequirement Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteRequirementById(Integer idRequirement) {
        requirementRepo.deleteById(idRequirement);
    }

} // end service implementation
