package com.ceiba.abono.servicio.testDataBuilder;

import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class AbonoTestDataBuilder {

    private static final LocalDate fechaActual = LocalDate.now();

    private Long id;
    private LocalDate fecha_abono;
    private int valor_abono;
    private Long prestamo;

    public AbonoTestDataBuilder(){
        this.fecha_abono = fechaActual;
        this.valor_abono = 250000;
        this.prestamo = 1L;
    }

    public AbonoTestDataBuilder conValorAbono(int valor_abono){
        this.valor_abono = valor_abono;
        return this;
    }

    public AbonoTestDataBuilder conPrestamo(Long prestamo){
        this.prestamo = prestamo;
        return this;
    }

}
