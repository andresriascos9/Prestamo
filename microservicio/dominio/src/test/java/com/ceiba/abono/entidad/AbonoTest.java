package com.ceiba.abono.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.abono.servicio.testDataBuilder.AbonoTestDataBuilder;
import com.ceiba.abonos.modelo.entidad.Abono;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbonoTest {

    private static final LocalDate fechaActual = LocalDate.now();

    @Test
    @DisplayName("Deberia crear correctamente el abono")
    void deberiaCrearCorrectamenteElAbono() {
        Abono abono = new AbonoTestDataBuilder().build();
        //assert
        assertEquals(fechaActual, abono.getFechaAbono());
        assertEquals(250000, abono.getValorAbono());
        assertEquals(1L, abono.getPrestamo());
    }

    @Test
    void deberiaFallarConValorAbonoNegativo() {

        //Arrange
        AbonoTestDataBuilder abonoTestDataBuilder = new AbonoTestDataBuilder().conValorAbono(-1055);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    abonoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El valor del abono debe ser positivo");
    }

    @Test
    void deberiaFallarSinIdValorPrestamo() {

        //Arrange
        AbonoTestDataBuilder abonoTestDataBuilder = new AbonoTestDataBuilder().conPrestamo(null);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    abonoTestDataBuilder.build();
                },
                ExcepcionValorObligatorio.class, "Se debe ingresar el id deL prestamo");
    }

    @Test
    void deberiaFallarConIdValorPrestamoNegativo() {

        //Arrange
        AbonoTestDataBuilder abonoTestDataBuilder = new AbonoTestDataBuilder().conPrestamo(-10L);
        //act-assert
        BasePrueba.assertThrows(() -> {
                    abonoTestDataBuilder.build();
                },
                ExcepcionValorInvalido.class, "El id deL prestamo debe ser positivo");
    }
}
