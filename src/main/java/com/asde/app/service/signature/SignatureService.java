package com.asde.app.service.signature;

import com.asde.app.domain.Signature;
import com.asde.app.repository.SignatureRepository;
import com.asde.app.service.signature.ISignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Clase de servicio que implementa los métodos de la interfaz {@link ISignatureService}, esta define el comportamiento
 * de los métodos de la interfaz, los cuales son las operaciones básicas CRUD a la tabla <b>firmas</b>.
 */
@Service
public class SignatureService implements ISignatureService{
    /* ~    PROPERTIES
    --------------------------------------------------- */
    private SignatureRepository signatureRepo;


    /* ~    CONSTRUCTOR
    --------------------------------------------------- */
    @Autowired
    public SignatureService(SignatureRepository signatureRepo) {
        this.signatureRepo = signatureRepo;
    }



    /* ~    METHODS
    --------------------------------------------------- */

    /**
     * Método que extrae de la BD todos los registros que obren en la tabla <b>firmas</b>.
     * @return Lista de objetos {@link Signature}.
     */
    @Override
    @Transactional(readOnly = true)
    public List<Signature> getAllSignatures() {
        return (List<Signature>) signatureRepo.findAll();
    }


    /**
     * Método que extrae de la BD un registro en especifico por medio de su clave de identificación única generada por
     * la tabla <b>firmas</b>.
     * @param idSignature Clave única de identificación.
     * @return Objeto {@link Signature} si existe algún registro o <b>null</b> en caso contrario.
     */
    @Override
    @Transactional(readOnly = true)
    public Signature findSignatureById(Integer idSignature) {
        return signatureRepo.findById(idSignature).orElse(null);
    }


    /**
     * Método que guarda la entidad {@link Signature} en la BD en la tabla <b>firmas</b>.
     * @param signature Objeto {@link Signature} a guardar.
     */
    @Override
    @Transactional(readOnly = false)
    public void saveSignature(Signature signature) {
        signatureRepo.save(signature);
    }


    /**
     * Método que elimina un registro en especifico de la tabla <b>firmas</b> dada una clave única
     * de identificación.
     * @param idSignature Clave única para identificar al registro a eliminar.
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteSignatureById(Integer idSignature) {
        signatureRepo.deleteById(idSignature);
    }

} // end service implementation
