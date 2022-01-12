package com.ceiba.persona.consulta;

import com.ceiba.persona.modelo.dto.DtoPersona;
import com.ceiba.persona.puerto.dao.DaoPersona;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarPersonas {

    private final DaoPersona daoPersona;

    public ManejadorListarPersonas(DaoPersona daoPersona){
        this.daoPersona = daoPersona;
    }

    public List<DtoPersona> ejecutar(){ return this.daoPersona.listar(); }
}
