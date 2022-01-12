package com.ceiba.prestamo.puerto.dao;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;

import java.util.List;

public interface DaoPrestamo {

    /**
     * Permite listar personas
     * @return las personas
     */
    List<DtoPrestamo> listar();
}
