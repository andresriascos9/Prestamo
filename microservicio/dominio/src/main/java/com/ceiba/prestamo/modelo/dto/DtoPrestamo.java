package com.ceiba.prestamo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoPrestamo {
    private Long id;
    private LocalDate fechaPago;
    private int valorPrestamo;
    private Long persona;
    private boolean estadoPrestamoPago;
}
