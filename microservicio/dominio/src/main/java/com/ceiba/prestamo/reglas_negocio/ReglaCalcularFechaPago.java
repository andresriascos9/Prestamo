package com.ceiba.prestamo.reglas_negocio;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class ReglaCalcularFechaPago {

    public static LocalDate sumarDiasHabiles(LocalDate fecha_inicio, int dias) {
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
