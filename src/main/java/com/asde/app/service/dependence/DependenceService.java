package com.asde.app.service.dependence;

import com.asde.app.domain.Dependence;
import com.asde.app.repository.DependenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IDependenceService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones basicas CRUD a la tabla <b>dependencias</b>.
 */

@Service
public class DependenceService implements IDependenceService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private DependenceRepository dependenceRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public DependenceService(DependenceRepository dependenceRepo) {
        this.dependenceRepo = dependenceRepo;
    }



    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>dependencias</b>.
     * @return Lista de objetos Dependence.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Dependence> getAllDependecies() {
        return dependenceRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única de la
     * tabla <b>dependencias</b>.
     * @param idDependence Clave única de identificación.
     * @return Objeto <b>Dependence</b> si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Dependence getDependencyById(Integer idDependence) {
        return dependenceRepo.findById(idDependence).orElse(null);
    }


    /**
     * Método que guarda la entidad <b>Dependence</b> en la BD en la tabla <b>dependencias</b>.
     * @param dependence Objeto <b>Dependence</b> a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveDependence(Dependence dependence) {
        dependenceRepo.save(dependence);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>dependencias</b> dada una clave única
     * de identificación.
     * @param idDependence Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteDependenceById(Integer idDependence) {
        dependenceRepo.deleteById(idDependence);
    }
} // end service implementation
