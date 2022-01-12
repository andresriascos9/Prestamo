package com.ceiba.persona.servicio.testDataBuilder;

import com.ceiba.persona.modelo.entidad.Persona;

public class PersonaTestDataBuilder {

    private Long id;
    private int identificacion;
    private String nombre;

    public PersonaTestDataBuilder() {
        this.identificacion = 1116269914;
        this.nombre = "Test Lorem Insup";
    }

    public PersonaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
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
        return new Persona(id,identificacion,nombre);
    }
}
