package com.ceiba.prestamo.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.persona.puerto.repositorio.RepositorioPersona;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

public class ServicioCrearPrestamo {

    private static final String EL_PRESTAMO_YA_EXISTE_EN_EL_SISTEMA = "La persona ya tiene un prestamo sin cancelar";
    private static final String LA_PERSONA_NO_EXISTE_EN_EL_SISTEMA = "La persona no existe";

    private final RepositorioPrestamo repositorioPrestamo;
    private final RepositorioPersona repositorioPersona;

    public ServicioCrearPrestamo(RepositorioPrestamo repositorioPrestamo, RepositorioPersona repositorioPersona) {
        this.repositorioPrestamo = repositorioPrestamo;
        this.repositorioPersona = repositorioPersona;
    }

    public Long ejecutar(Prestamo prestamo) {
        validarExistenciaPreviaPersonaConPrestamo(prestamo);
        validarExistenciaPersona(prestamo.getPersona());
        prestamo.setEstadoPrestamoPago(false);
        return this.repositorioPrestamo.crear(prestamo);
    }

    private void validarExistenciaPreviaPersonaConPrestamo(Prestamo prestamo) {
        boolean existe = this.repositorioPrestamo.existePersonaPorIdConPrestamoSinCancelar(prestamo.getPersona());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PRESTAMO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }

    private void validarExistenciaPersona(Long persona) {
        boolean existe = this.repositorioPersona.existePersonaPorId(persona);
        if(!existe) {
            throw new ExcepcionValorInvalido(LA_PERSONA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

}
