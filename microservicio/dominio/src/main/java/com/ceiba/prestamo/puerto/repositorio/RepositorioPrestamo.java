package com.ceiba.prestamo.puerto.repositorio;

import com.ceiba.prestamo.modelo.dto.DtoPrestamo;
import com.ceiba.prestamo.modelo.entidad.Prestamo;

public interface RepositorioPrestamo {

    /**
     * Permite crear una prestamo
     * @param prestamo
     * @return el id generado
     */
    Long crear(Prestamo prestamo);

    /**
     * Permite actualizar un prestamo
     * @param prestamo
     */
    void actualizar(Long prestamo, boolean estadoPago);

    /**
     * Permite validar si existe un prestamo por id
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un prestamo por id
     * @return si existe o no
     */
    boolean obtenerEstadoPrestamo(Long id);

    /**
     * Permite validar si existe un prestamo sin pago de una persona por id
     * @return si existe o no
     */
    boolean existePersonaPorIdConPrestamoSinCancelar(Long id);

    /**
     * Permite obtener el prestamo
     * @return dtoPrestamo
     */
    DtoPrestamo obteneroPrestamo(Long prestamo);
}
