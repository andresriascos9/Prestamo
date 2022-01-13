package com.ceiba.prestamo.puerto.repositorio;

import com.ceiba.prestamo.modelo.entidad.Prestamo;

public interface RepositorioPrestamo {

    /**
     * Permite crear una prestamo
     * @param prestamo
     * @return el id generado
     */
    Long crear(Prestamo prestamo);

    /**
     * Permite actualizar un usuario
     * @param prestamo
     */
    void actualizar(Prestamo prestamo);

    /**
     * Permite validar si existe un usuario con un nombre excluyendo un id
     * @return si existe o no
     */
    boolean existePorId(Long id);

    /**
     * Permite validar si existe un prestamo sin pago de una persona por id
     * @return si existe o no
     */
    boolean existePersonaPorIdConPrestamoSinCancelar(Long id);
}