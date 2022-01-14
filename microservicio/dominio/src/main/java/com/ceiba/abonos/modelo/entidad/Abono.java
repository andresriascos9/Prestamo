package com.ceiba.abonos.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Abono {

    private static final String VALOR_ABONO_DEBE_SER_POSITIVO = "El valor del abono debe ser positivo";
    private static final String SE_DEBE_INGRESAR_ID_PRESTAMO= "Se debe ingresar el id deL prestamo";
    private static final String PRESTAMO_DEBE_SER_POSITIVO = "El id deL prestamo debe ser positivo";
    private static final LocalDate fechaActual = LocalDate.now();

    private Long id;
    private LocalDate fechaAbono;
    private int valorAbono;
    private Long prestamo;

    public Abono (Long id, int valorAbono, Long prestamo){

        validarObligatorio(prestamo,SE_DEBE_INGRESAR_ID_PRESTAMO);
        validarPositivo((double) valorAbono,VALOR_ABONO_DEBE_SER_POSITIVO);
        validarPositivo((double) prestamo,PRESTAMO_DEBE_SER_POSITIVO);

        this.id = id;
        this.fechaAbono = fechaActual;
        this.valorAbono = valorAbono;
        this.prestamo = prestamo;

    }

}
