package com.ceiba.abono.servicio.testDataBuilder;

import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.persona.servicio.testDataBuilder.PersonaTestDataBuilder;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;


public class AbonoTestDataBuilder {

    private static final LocalDate fechaActual = LocalDate.now();

    private Long id;
    private LocalDate fechaAbono;
    private int valorAbono;
    private Long prestamo;

    public AbonoTestDataBuilder(){
        this.fechaAbono = fechaActual;
        this.valorAbono = 250000;
        this.prestamo = 1L;
    }

    public AbonoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public AbonoTestDataBuilder conValorAbono(int valorAbono){
        this.valorAbono = valorAbono;
        return this;
    }

    public AbonoTestDataBuilder conPrestamo(Long prestamo){
        this.prestamo = prestamo;
        return this;
    }

    public Abono build() {
        return new Abono(id,valorAbono,prestamo);
    }

    public Abono buildConValorAbono(int valorAbono) {
        return new Abono(id,valorAbono,prestamo);
    }

}
