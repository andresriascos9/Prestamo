package com.ceiba.prestamo.servicio;

import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;

import java.time.LocalDate;

public class ServicioCrearPrestamo {

    private static final String EL_PRESTAMO_YA_EXISTE_EN_EL_SISTEMA = "La persona ya tiene un prestamo sin cancelar";

    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo) {
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Long ejecutar(Prestamo prestamo) {
        validarExistenciaPrevia(prestamo);
        prestamo.setEstadoPrestamoPago(false);
        return this.repositorioPrestamo.crear(prestamo);
    }

    private void validarExistenciaPrevia(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existePersonaPorIdConPrestamoSinCancelar(prestamo.getPersona());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PRESTAMO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

}
