package com.asde.app.service.asde;

import com.asde.app.domain.Asde;
import com.asde.app.repository.AsdeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Clase de servicio que implementa los métodos de la interfaz {@link IAsdeService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones basicas CRUD a la tabla <b>asde</b>.
 */

@Service
public class AsdeService implements IAsdeService{

    /* ~    PROPERTIES
    --------------------------------------------------- */
    private AsdeRepository asdeRepo;



    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public AsdeService(AsdeRepository asdeRepo) {
        this.asdeRepo = asdeRepo;
    }



    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que obtiene el primer registro de la <b>asde</b> ya que esta tabla deberá contener un solo registro.
     * @return Objeto Asde con la información de la empresa.
     */
    @Override
    @Transactional(readOnly = true)
    public Asde getAsde() {
        List<Asde> asde = (List<Asde>) asdeRepo.findAll();
        return asde.stream().findFirst().orElse(null);
    }

    
    /**
     * Método que permite actualizar el registro de la tabla <b>asde</b> en la BD.
     * @param asde Objeto <b>Asde</b> que sera actualizado.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveAsde(Asde asde) {
        asdeRepo.save(asde);
    }
}
