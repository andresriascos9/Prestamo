package com.ceiba.persona.modelo.entidad;

import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Persona {

    private static final String IDENTIFICACION_DEBE_POSITIVO = "El número de identificación debe ser positivo";
    private static final String SE_DEBE_INGRESAR_NUMERO_IDENTIFICACION = "Se debe agregar un numero de identificación";
    private static final String SE_DEBE_INGRESAR_NOMBRE= "Se debe agregar un nombre completo";

    private int identificacion;
    private String nombre;

    public Persona(int identificacion, String nombre) {

        validarObligatorio(identificacion,SE_DEBE_INGRESAR_NUMERO_IDENTIFICACION);
        validarObligatorio(identificacion,SE_DEBE_INGRESAR_NOMBRE);
        validarPositivo((double) identificacion,IDENTIFICACION_DEBE_POSITIVO);

        this.identificacion = identificacion;
        this.nombre = nombre;
    }


}
