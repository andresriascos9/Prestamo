package com.ceiba.persona.servicio.testDataBuilder;

import com.ceiba.persona.modelo.entidad.Persona;

public class PersonaTestDataBuilder {

    private int identificacion;
    private String nombre;

    public PersonaTestDataBuilder() {
        this.identificacion = 1116269914;
        this.nombre = "Test Lorem Insup";
    }

    public PersonaTestDataBuilder conIdentificacion(int identificacion) {
        this.identificacion = identificacion;
        return this;
    }

    public PersonaTestDataBuilder conNombre(String nombre) {
        this.nombre = nombre;
        return this;
    }

    public Persona build() {
        return new Persona(identificacion,nombre);
    }
}
