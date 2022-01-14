package com.ceiba.prestamo.modelo.entidad;

import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Prestamo {

    private static final String VALOR_PRESTAMO_DEBE_SER_POSITIVO = "El valor del prestamo debe ser positivo";
    private static final String SE_DEBE_INGRESAR_ID_PERSONA= "Se debe ingresar el id de la persona";
    private static final String PERSONA_DEBE_SER_POSITIVO = "El id de la persona debe ser positivo";
    private static final LocalDate FECHA_ACTUAL = LocalDate.now();
    private static final int DIAS_HABILES_A_SUMAR = 10;

    private Long id;
    private LocalDate fechaPago;
    private int valorPrestamo;
    private Long persona;
    private boolean estadoPrestamoPago;

    public Prestamo(Long id, int valorPrestamo, Long persona){

        validarObligatorio(persona,SE_DEBE_INGRESAR_ID_PERSONA);
        validarPositivo((double) valorPrestamo,VALOR_PRESTAMO_DEBE_SER_POSITIVO);
        validarPositivo((double) persona,PERSONA_DEBE_SER_POSITIVO);

        this.id = id;
        this.fechaPago = sumarDiasHabiles(FECHA_ACTUAL, DIAS_HABILES_A_SUMAR);
        this.valorPrestamo = valorPrestamo;
        this.persona = persona;
    }

    public Prestamo(Long id, boolean estadoPrestamoPago) {
        this.id = id;
        this.estadoPrestamoPago = estadoPrestamoPago;
    }

    public boolean getEstadoPrestamoPago() {
        return estadoPrestamoPago;
    }

    public void setEstadoPrestamoPago(boolean estadoPrestamoPago){
        this.estadoPrestamoPago = estadoPrestamoPago;
    }

    public void setFechaPago(LocalDate fechaPago){
        this.fechaPago = fechaPago;
    }

    private LocalDate sumarDiasHabiles(LocalDate fechaInicio, int dias) {
        if (dias < 1) {
            return fechaInicio;
        }
        LocalDate fechaCalculada = fechaInicio;
        int diasSumados = 0;
        while (diasSumados < dias) {
            fechaCalculada = fechaCalculada.plusDays(1);
            if (!(fechaCalculada.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    fechaCalculada.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++diasSumados;
            }
        }
        return fechaCalculada;
    }

}
