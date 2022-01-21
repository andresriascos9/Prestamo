package com.ceiba.abonos.servicio;

import com.ceiba.abonos.puerto.repositorio.RepositorioAbono;
import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.puerto.repositorio.RepositorioPrestamo;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;

import java.time.LocalDate;


public class ServicioCrearAbono {

    private static final String EL_ABONO_ES_MAYOR_AL_PRESTAMO = "El abono es mayor a el saldo del préstamo";
    private static final String DESCUENTO_POR_PAGO_ANTES = "Tienes un descuento por pago anticipado, su valor a pagar es: $%s";
    private static final String MORA_POR_PAGO_VENCIDO = "Tienes una tasa de mora por pago vencido, su valor a pagar es: $%s";
    private static final String EL_PRESTAMO_NO_EXISTE = "El préstamo no existe en el sistema";
    private static final String EL_PRESTAMO_ESTA_CANCELADO= "El préstamo ya está cancelado";
    private static final boolean BOOLEAN_ESTADO_PRESTAMO_PAGO = true;
    private static final LocalDate FECHA_ACTUAL = LocalDate.now();
    private static final int PORCENTAJE_DESCUENTO_PAGO_ANTICIPADO = 5;
    private static final int PORCENTAJE_MORA_PAGO_VENCIDO = 4;

    private final RepositorioAbono repositorioAbono;
    private final RepositorioPrestamo repositorioPrestamo;

    public ServicioCrearAbono(RepositorioAbono repositorioAbono, RepositorioPrestamo repositorioPrestamo) {
        this.repositorioAbono = repositorioAbono;
        this.repositorioPrestamo = repositorioPrestamo;
    }

    public Long ejecutar(Abono abono) {
        validarExistenciaPreviaPrestamo(abono.getPrestamo());
        validarPrestamoCancelado(abono.getPrestamo());
        validarReglasPrestamo(abono.getValorAbono(), abono.getPrestamo());
        return this.repositorioAbono.crear(abono);
    }

    private void validarPrestamoCancelado(Long prestamo){
        boolean existe = this.repositorioPrestamo.obtenerEstadoPrestamo(prestamo);
        if(existe) {
            throw new ExcepcionValorInvalido(EL_PRESTAMO_ESTA_CANCELADO);
        }
    }

    private void validarExistenciaPreviaPrestamo(Long prestamo){
        boolean existe = this.repositorioPrestamo.existePorId(prestamo);
        if(!existe) {
            throw new ExcepcionValorInvalido(EL_PRESTAMO_NO_EXISTE);
        }
    }

    private void validarReglasPrestamo(int valorAbono, Long prestamo){
        DtoPrestamo dtoPrestamo = this.repositorioPrestamo.obteneroPrestamo(prestamo);
        int valorPrestamo = dtoPrestamo.getValorPrestamo();
        int abonosAnteriores = sumarAbonosAnteriores(prestamo);
        LocalDate fechaPago = dtoPrestamo.getFechaPago();
        int valorDebe = valorPrestamo - abonosAnteriores;
        int valorAPagar = calcularValorAPagar(valorPrestamo, valorDebe, fechaPago);
        if((valorAbono > valorAPagar) && (abonosAnteriores == 0) && ( fechaPago.isAfter(FECHA_ACTUAL) || fechaPago.isEqual(FECHA_ACTUAL))){
            reglaUnSoloPagoAntesDeFechaObtieneDescuento(valorAPagar);
        }else if((fechaPago.isBefore(FECHA_ACTUAL)) && (valorAbono < valorAPagar)){
            reglaPagoDespuesDeFechaObtieneMoraEnSaldo(valorAPagar);
        }else if(valorAPagar < valorAbono) {
            throw new ExcepcionValorInvalido(EL_ABONO_ES_MAYOR_AL_PRESTAMO);
        }else if(valorAPagar == valorAbono){
            actualizarEstadoPrestamoPorPagoTotal(prestamo);
        }
    }

    private int sumarAbonosAnteriores(Long prestamo){
        return this.repositorioAbono.sumarAbonos(prestamo);
    }

    private void actualizarEstadoPrestamoPorPagoTotal(Long prestamo){
        this.repositorioPrestamo.actualizar(prestamo, BOOLEAN_ESTADO_PRESTAMO_PAGO);
    }

    private void reglaUnSoloPagoAntesDeFechaObtieneDescuento(int valorAPagar){
        throw new ExcepcionValorInvalido(String.format(DESCUENTO_POR_PAGO_ANTES, valorAPagar));
    }

    private void reglaPagoDespuesDeFechaObtieneMoraEnSaldo(int valorAPagar){
        throw new ExcepcionValorInvalido(String.format(MORA_POR_PAGO_VENCIDO, valorAPagar));
    }

    private int calcularValorAPagar(int valorPrestamo, int valorDebe, LocalDate fechaPago){
        int valorAPagar;
        if((valorPrestamo == valorDebe) && (fechaPago.isAfter(FECHA_ACTUAL) || fechaPago.isEqual(FECHA_ACTUAL)) ){
            valorAPagar = (int) (valorPrestamo*(1-(PORCENTAJE_DESCUENTO_PAGO_ANTICIPADO*0.01)));
        }else if (fechaPago.isBefore(FECHA_ACTUAL)){
            valorAPagar = (int) (valorDebe*(1+(PORCENTAJE_MORA_PAGO_VENCIDO*0.01)));
        }else{
            valorAPagar = valorDebe;
        }
        return valorAPagar;
    }


}
