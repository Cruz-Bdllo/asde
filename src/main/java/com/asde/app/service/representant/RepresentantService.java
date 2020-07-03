package com.asde.app.service.representant;

import com.asde.app.domain.Representant;
import com.asde.app.repository.RepresentantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IRepresentantService}, esta define el
 * comportamiento de los métodos de la interfaz, los cuales son las operaciones básicas CRUD a la tabla
 * <b>representantes</b>.
 */
@Service
public class RepresentantService implements IRepresentantService {

    /* ~    PROPERTIES
   --------------------------------------------------- */
    private RepresentantRepository repreRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public RepresentantService(RepresentantRepository repreRepo){
        this.repreRepo = repreRepo;
    }



    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>representantes</b>.
     * @return Lista de objetos {@link Representant}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Representant> getAllRepresentants() {
         return (List<Representant>) repreRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existRepresentantByRFC(String rfc) {
        Representant repre = repreRepo.findRepresentantByRfc(rfc).orElse(null);
        return (repre != null) ? true : false;
    }

    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única generada por
     * la tabla <b>representantes</b>.
     * @param idRepresentant Clave única de identificación.
     * @return Objeto {@link Representant} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Representant getRepresentantById(Integer idRepresentant) {
        return repreRepo.findById(idRepresentant).orElse(null);
    }


    /**
     * Método que guarda la entidad {@link Representant} en la BD en la tabla <b>representantes</b>.
     * @param representant Objeto {@link Representant} a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveRepresentant(Representant representant) {
        repreRepo.save(representant);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>representantes</b> dada una clave única
     * de identificación.
     * @param IdRepresentant Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteRepresentantById(Integer IdRepresentant) {
        repreRepo.deleteById(IdRepresentant);
    }
}
