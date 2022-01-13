package com.ceiba.abonos.puerto.repositorio;

import com.ceiba.abonos.modelo.entidad.Abono;

public interface RepositorioAbono {

    /**
     * Permite crear una abono
     * @param abono
     * @return el id generado
     */
    Long crear(Abono abono);

    int sumarAbonos(Long prestamo);

}
