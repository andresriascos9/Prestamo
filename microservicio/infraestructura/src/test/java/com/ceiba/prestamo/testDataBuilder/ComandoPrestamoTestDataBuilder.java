package com.ceiba.prestamo.testDataBuilder;

import com.ceiba.prestamo.comando.ComandoPrestamo;

public class ComandoPrestamoTestDataBuilder {

    private Long id;
    private int valorPrestamo;
    private Long persona;

    public ComandoPrestamoTestDataBuilder() {
        valorPrestamo = 3000000;
        persona = 2L;
    }

    public ComandoPrestamo build() {
        return new ComandoPrestamo(id,valorPrestamo, persona);
    }

    public ComandoPrestamo buildConPersona(Long persona) {
        return new ComandoPrestamo(id,valorPrestamo, persona);
    }

}
