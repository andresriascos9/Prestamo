package com.ceiba.persona.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.persona.modelo.entidad.Persona;
import com.ceiba.persona.puerto.repositorio.RepositorioPersona;

public class ServicioCrearPersona {

    private static final String LA_PERSONA_EXISTE = "Ya existe esta persona";

    private final RepositorioPersona repositorioPersona;

    public ServicioCrearPersona(RepositorioPersona repositorioPersona) {
        this.repositorioPersona = repositorioPersona;
    }

    public int ejecutar(Persona persona){
        existePersona(persona.getIdentificacion());
        return this.repositorioPersona.crear(persona);

    }

    private void existePersona(int numeroIdentificacion){
        if(repositorioPersona.existe(numeroIdentificacion)){
            throw new ExcepcionDuplicidad(LA_PERSONA_EXISTE);
        }
    }
}
