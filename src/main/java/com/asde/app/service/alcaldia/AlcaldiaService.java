package com.asde.app.service.alcaldia;

import com.asde.app.domain.Alcaldia;
import com.asde.app.repository.AlcaldiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IAlcaldiaService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones basicas CRUD a la tabla <b>alcaldias</b>.
 */
@Service
public class AlcaldiaService implements IAlcaldiaService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private AlcaldiaRepository alcaldiaRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public AlcaldiaService (AlcaldiaRepository alcaldiaRepo) {
        this.alcaldiaRepo = alcaldiaRepo;
    }


    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>alcaldias</b>.
     * @return Lista de objetos Alcaldia.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Alcaldia> getAllAlcaldias() {
        return (List<Alcaldia>) alcaldiaRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única de la
     * tabla <b>alcaldias</b>.
     * @param idAlcaldia Clave única de identificación.
     * @return Objeto <b>Alcaldia</b> si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Alcaldia getAlcaldiaById(Integer idAlcaldia) {
        return alcaldiaRepo.findById(idAlcaldia).orElse(null);
    }


    /**
     * Método que guarda la entidad <b>Alcaldia</b> en la BD en la tabla <b>alcaldias</b>.
     * @param alcaldia Objeto <b>Alcaldia</b> a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAlcaldia(Alcaldia alcaldia) {
        alcaldiaRepo.save(alcaldia);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>alcaldias</b> dada una clave única
     * de identificación.
     * @param idAlcaldia Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteAlcaldia(Integer idAlcaldia) {
        alcaldiaRepo.deleteById(idAlcaldia);
    }

} // end service to alcaldia
