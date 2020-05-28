package com.asde.app.exceptions;

public class ProcedureNameDuplicateException extends RuntimeException{

    private static long serialVersionUID=2L;

    public ProcedureNameDuplicateException(String name) {
        super("El tramite con nombre "+name+" ya existe");
    }
}
