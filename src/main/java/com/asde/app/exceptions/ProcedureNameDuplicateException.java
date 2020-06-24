package com.asde.app.exceptions;

/**
 * Clase que manejará las excepciones de tipo <b>ProcedureNameDuplicateException</b>.
 */
public class ProcedureNameDuplicateException extends RuntimeException{

    private static long serialVersionUID=2L;

    /**
     * Método que lanza la exepción cuando se intenta almacenar de forma repetida un identificador único en la tabla.
     * @param name String nombre del trámite que se intenta duplicar.
     */
    public ProcedureNameDuplicateException(String name) {
        super("El tramite con nombre "+name+" ya existe");
    }
}
