package com.ceiba.prestamo.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.persona.servicio.testDataBuilder.PersonaTestDataBuilder;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrestamoTest {

    private static final LocalDate fechaActual = LocalDate.now();
    private static final int diasHabilesASumar = 10;

    @Test
    @DisplayName("Deberia crear correctamente la persona")
    void deberiaCrearCorrectamenteElPrestamo() {
        Prestamo prestamo = new PrestamoTestDataBuilder().conId(1L).build();
        //assert
        assertEquals(1, prestamo.getId());
        assertEquals(sumarDiasHabiles(fechaActual, diasHabilesASumar), prestamo.getFechaPago());
        assertEquals(3000000, prestamo.getValorPrestamo());
        assertEquals(1L, prestamo.getPersona());
        assertEquals(false, prestamo.getEstadoPrestamoPago());
    }

    @Test
    @DisplayName("Deberia crear correctamente la persona")
    void deberiaCrearCorrectamenteElPrestamoCon2Parametros() {
        Prestamo prestamo = new PrestamoTestDataBuilder().conId(1L).buildSinParametros();
        //assert
        assertEquals(1, prestamo.getId());
        assertEquals(false, prestamo.getEstadoPrestamoPago());
    }

    @Test
    void deberiaFallarConValorPrestamoNegativo() {

        //Arrange
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conValorPrestamo(-1055);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    prestamoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El valor del prestamo debe ser positivo");
    }

    @Test
    void deberiaFallarConPersonaNegativo() {

        //Arrange
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conPersona(-10L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    prestamoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El id de la persona debe ser positivo");
    }

    @Test
    void deberiaFallarConPersonaVacio() {
        //Arrange
        PrestamoTestDataBuilder prestamoTestDataBuilder = new PrestamoTestDataBuilder().conPersona(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    prestamoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id de la persona");
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
