package com.ceiba.abono.entidad;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.prestamo.modelo.entidad.Prestamo;
import com.ceiba.persona.servicio.testDataBuilder.PersonaTestDataBuilder;
import com.ceiba.prestamo.servicio.testDataBuilder.PrestamoTestDataBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbonoTest {

    private static final LocalDate fechaActual = LocalDate.now();
}
