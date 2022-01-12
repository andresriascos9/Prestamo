package com.ceiba.prestamo.modelo.entidad;

import lombok.Getter;

import java.time.LocalDate;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Prestamo {

    private static final String VALOR_PRESTAMO_DEBE_SER_POSITIVO = "El valor del prestamo debe ser positivo";
    private static final String SE_DEBE_INGRESAR_ID_PERSONA= "Se debe ingresar el id de la persona";
    private static final String PERSONA_DEBE_SER_POSITIVO = "El id de la persona debe ser positivo";

    private Long id;
    private LocalDate fecha_pago;
    private int valor_prestamo;
    private Long persona;
    private boolean estado_prestamo_pago;

    public Prestamo(Long id, LocalDate fecha_pago, int valor_prestamo, Long persona, boolean estado_prestamo_pago){

        validarObligatorio(persona,SE_DEBE_INGRESAR_ID_PERSONA);
        validarPositivo((double) valor_prestamo,VALOR_PRESTAMO_DEBE_SER_POSITIVO);
        validarPositivo((double) persona,PERSONA_DEBE_SER_POSITIVO);

        this.id = id;
        this.fecha_pago = fecha_pago;
        this.valor_prestamo = valor_prestamo;
        this.persona = persona;
        this.estado_prestamo_pago = estado_prestamo_pago;
    }


    public boolean getEstadoPrestamoPago() {
        return estado_prestamo_pago;
    }

    public void setFechaPago(LocalDate fecha_pago){
        this.fecha_pago = fecha_pago;
    }

    public void setEstado_prestamo_pago(boolean estado_prestamo_pago){
        this.estado_prestamo_pago = estado_prestamo_pago;
    }
}
