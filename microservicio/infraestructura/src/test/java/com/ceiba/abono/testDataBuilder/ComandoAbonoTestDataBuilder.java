package com.ceiba.abono.testDataBuilder;

import com.ceiba.abono.comando.ComandoAbono;

public class ComandoAbonoTestDataBuilder {

    private Long id;
    private int valorAbono;
    private Long prestamo;

    public ComandoAbonoTestDataBuilder(){
        valorAbono = 2000000;
        prestamo = 1L;
    }

    public ComandoAbono build() {
        return new ComandoAbono(id,valorAbono, prestamo);
    }

    public ComandoAbono buildConPrestamo(Long prestamo) {
        return new ComandoAbono(id,valorAbono, prestamo);
    }
}
