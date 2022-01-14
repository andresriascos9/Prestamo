package com.ceiba.abono.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoAbono {

    private Long id;
    private int valorAbono;
    private Long prestamo;

}
