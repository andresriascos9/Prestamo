package com.ceiba.persona.testDataBuilder;

import com.ceiba.persona.comando.ComandoPersona;
import java.util.UUID;

public class ComandoPersonaTestDataBuilder {

    private Long id;
    private int identificacion;
    private String nombre;

    public ComandoPersonaTestDataBuilder() {
        identificacion = 123456;
        nombre = UUID.randomUUID().toString();
    }

    public ComandoPersonaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public ComandoPersonaTestDataBuilder conIdentificacion(int identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public ComandoPersona build() {
        return new ComandoPersona(id,identificacion, nombre);
    }

    public ComandoPersona buildConId(int identificacion) {
        return new ComandoPersona(id,identificacion, nombre);
    }

}
