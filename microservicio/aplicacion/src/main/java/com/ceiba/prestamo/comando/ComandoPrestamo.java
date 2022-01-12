package com.ceiba.prestamo.comando;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPrestamo {

    private Long id;
    private LocalDate fecha_pago;
    private int valor_prestamo;
    private Long persona;
    private boolean estado_prestamo_pago;

    public boolean getEstadoPrestamoPago() {
        return estado_prestamo_pago;
    }

}
