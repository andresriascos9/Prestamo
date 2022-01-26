package com.ceiba.prestamo.servicio.testDataBuilder;

import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.usuario.servicio.testdatabuilder.UsuarioTestDataBuilder;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class PrestamoTestDataBuilder {

    private static final LocalDate fechaActual = LocalDate.now();
    private static final int diasHabilesASumar = 10;

    private Long id;
    private LocalDate fecha_pago;
    private int valor_prestamo;
    private Long persona;
    private boolean estado_prestamo_pago;

    public PrestamoTestDataBuilder(){
        this.fecha_pago = sumarDiasHabiles(fechaActual, diasHabilesASumar);
        this.valor_prestamo = 3000000;
        this.persona = 1L;
        this.estado_prestamo_pago = false;
    }

    public PrestamoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public PrestamoTestDataBuilder conValorPrestamo(int valor_prestamo){
        this.valor_prestamo = valor_prestamo;
        return this;
    }

    public PrestamoTestDataBuilder conPersona(Long persona){
        this.persona = persona;
        return this;
    }

    public Prestamo build() {
        return new Prestamo(id,valor_prestamo,persona);
    }

    public Prestamo buildSinParametros() {
        return new Prestamo(id,estado_prestamo_pago);
    }

    public LocalDate sumarDiasHabiles(LocalDate fecha_inicio, int dias) {
        if (dias < 1) {
            return fecha_inicio;
        }
        LocalDate fecha_calculada = fecha_inicio;
        int diasSumados = 0;
        while (diasSumados < dias) {
            fecha_calculada = fecha_calculada.plusDays(1);
            if (!(fecha_calculada.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    fecha_calculada.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diasSumados;
            }
        }
        return fecha_calculada;
    }

}
