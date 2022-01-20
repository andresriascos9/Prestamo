package com.ceiba.infraestructura.excepcion;

public class ExcepcionTrm extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ExcepcionTrm(String message, Exception e) {
        super(message,e);
    }
}
