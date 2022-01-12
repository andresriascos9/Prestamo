package com.ceiba.prestamo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;

public class ServicioActualizarPrestamo {

    private static final String EL_PRESTAMO_NO_EXISTE_EN_EL_SISTEMA = "El prestamo no existe en el sistema";

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioActualizarPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public void ejecutar(Prestamo prestamo) {
        validarExistenciaPrevia(prestamo);
        this.repositorioPrestamo.actualizar(prestamo);
    }

    private void validarExistenciaPrevia(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existePorId(prestamo.getId());
        if(!existe) {
            throw new ExcepcionDuplicidad(EL_PRESTAMO_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
