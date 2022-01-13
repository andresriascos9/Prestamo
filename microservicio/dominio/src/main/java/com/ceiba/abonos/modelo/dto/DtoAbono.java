package com.ceiba.abonos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoAbono {
    private Long id;
    private LocalDate fecha_abono;
    private int valor_abono;
    private Long prestamo;
}
