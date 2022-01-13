package com.ceiba.abonos.servicio;

import com.ceiba.abonos.puerto.repositorio.RepositorioAbono;
import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.time.LocalDate;


public class ServicioCrearAbono {

    private static final String EL_ABONO_ES_MAYOR_AL_PRESTAMO = "El abono es mayor a el saldo del préstamo";
    private static final String EL_PRESTAMO_NO_EXISTE = "El préstamo no existe en el sistema";
    private static final LocalDate fechaActual = LocalDate.now();

    private final RepositorioAbono repositorioAbono;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioCrearAbono(RepositorioAbono repositorioAbono, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioAbono = repositorioAbono;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Long ejecutar(Abono abono) {
        validarExistenciaPreviaPrestamo(abono.getPrestamo());
        validarValorPrestamo(abono.getValor_abono(), abono.getPrestamo());
        abono.setFecha_abono(fechaActual);
        return this.repositorioAbono.crear(abono);
    }

    private void validarExistenciaPreviaPrestamo(Long prestamo){
        boolean existe = this.repositorioPrestamo.existePorId(prestamo);
        if(!existe) {
            throw new ExcepcionValorInvalido(EL_PRESTAMO_NO_EXISTE);
        }
    }

    private void validarValorPrestamo(int valor_abono, Long prestamo){
        int valor_prestamo = this.repositorioPrestamo.obtenerPrestamo(prestamo);
        int valor_debe = valor_prestamo - sumarAbonosAnteriores(prestamo);
        if(valor_debe < valor_abono) {
            throw new ExcepcionValorInvalido(EL_ABONO_ES_MAYOR_AL_PRESTAMO);
        } // validar fecha de pago para cobro de mora o descuento y también cambiar el estado del prestamo a true porque ya fue pago.
    }

    private int sumarAbonosAnteriores(Long prestamo){
        return this.repositorioAbono.sumarAbonos(prestamo);
    }

}
