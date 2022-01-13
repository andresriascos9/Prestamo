package com.ceiba.abonos.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Abono {

    private static final String VALOR_ABONO_DEBE_SER_POSITIVO = "El valor del abono debe ser positivo";
    private static final String SE_DEBE_INGRESAR_ID_PRESTAMO= "Se debe ingresar el id deL prestamo";
    private static final String PRESTAMO_DEBE_SER_POSITIVO = "El id deL prestamo debe ser positivo";

    private Long id;
    private LocalDate fecha_abono;
    private int valor_abono;
    private Long prestamo;

    public Abono (Long id, LocalDate fecha_abono, int valor_abono, Long prestamo){

        validarObligatorio(prestamo,SE_DEBE_INGRESAR_ID_PRESTAMO);
        validarPositivo((double) valor_abono,VALOR_ABONO_DEBE_SER_POSITIVO);
        validarPositivo((double) prestamo,PRESTAMO_DEBE_SER_POSITIVO);

        this.id = id;
        this.fecha_abono = fecha_abono;
        this.valor_abono = valor_abono;
        this.prestamo = prestamo;

    }

    public void setFecha_abono(LocalDate fecha_abono){
        this.fecha_abono = fecha_abono;
    }
}
