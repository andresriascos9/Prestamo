package com.ceiba.abono.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoAbono {

    private Long id;
    private LocalDate fecha_abono;
    private int valor_abono;
    private Long prestamo;

}
