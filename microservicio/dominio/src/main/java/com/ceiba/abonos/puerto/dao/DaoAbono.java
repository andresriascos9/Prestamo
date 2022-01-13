package com.ceiba.abonos.puerto.dao;

import com.ceiba.abonos.modelo.dto.DtoAbono;

import java.util.List;

public interface DaoAbono {

    /**
     * Permite listar abonos
     * @return los abonos
     */
    List<DtoAbono> listar();
}
