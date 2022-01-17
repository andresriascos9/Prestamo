package com.ceiba.persona.puerto.repositorio;

import com.ceiba.persona.modelo.entidad.Persona;

public interface RepositorioPersona {

    /**
     * Permite crear una persona
     * @param persona
     * @return el id generado
     */
    Long crear(Persona persona);

    /**
     * Permite validar si existe una persona por su identificacion
     * @param identificacion
     * @return si existe o no
     */
    boolean existe(int identificacion);

    /**
     * Permite validar si existe una persona por su identificacion
     * @param id
     * @return si existe o no
     */
    boolean existePersonaPorId(Long id);
}
