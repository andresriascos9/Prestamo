package com.ceiba.persona.comando.fabrica;

import com.ceiba.persona.comando.ComandoPersona;
import com.ceiba.persona.modelo.entidad.Persona;
import org.springframework.stereotype.Component;

@Component
public class FabricaPersona {

    public Persona crear (ComandoPersona comandoPersona){
        return new Persona(comandoPersona.getId(), comandoPersona.getIdentificacion(), comandoPersona.getNombre());
    }
}
