package com.asde.app.service.signature;

import com.asde.app.domain.Requirement;
import com.asde.app.domain.Signature;

import java.util.List;

/**
 * Interfaz que permite definir los métodos que podrán usar las clase, servicios, componentes o controladores para
 * acceder a la BD y manipular la tabla <b>firmas</b>, definiendo nombres mas comodos a los que ofrece la
 * interfaz CRUD {@link org.springframework.data.repository.CrudRepository}.
 */
public interface ISignatureService {

    /**
     * Obtendrá todos los registros de la tabla <b>firmas</b> en la BD.
     * @return Lista de objetos de la entidad {@link Signature}.
     */
    List<Signature> getAllSignatures();


    /**
     * Obtendrá un registro en especifico definido en su parámetro y este sera el id auto generado por la BD.
     * @param idSignature Integer Identificador único a buscar.
     * @return Objeto de la entidad {@link Signature}.
     */
    Signature findSignatureById(Integer idSignature);


    /**
     * Guardará la entidad {@link Signature} como registro en la tabla <b>firmas</b> de la BD.
     * @param signature Objeto de la entidad {@link Signature} a guardar.
     */
    void saveSignature(Signature signature);


    /**
     * Eliminará un registro de la tabla <b>firmas</b> dado su identificador único en su parámetro.
     * @param idSignature Identificador único a eliminar.
     */
    void deleteSignatureById(Integer idSignature);

}
