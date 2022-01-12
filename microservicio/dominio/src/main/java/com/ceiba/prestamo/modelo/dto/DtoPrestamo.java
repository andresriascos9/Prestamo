package com.ceiba.prestamo.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoPrestamo {
    private Long id;
    private LocalDate fecha_pago;
    private int valor_prestamo;
    private Long persona;
    private boolean estado_prestamo_pago;
}
