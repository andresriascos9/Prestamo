package com.ceiba.abonos.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoAbono {
    private Long id;
    private LocalDate fechaAbono;
    private int valorAbono;
    private Long prestamo;
}
